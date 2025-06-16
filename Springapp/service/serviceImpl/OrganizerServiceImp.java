package com.examly.springapp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Player;
import com.examly.springapp.entity.Team;
import com.examly.springapp.exception.ExceedsTeamBudgetException;
import com.examly.springapp.exception.PlayerAlreadyAssignedException;
import com.examly.springapp.repository.PlayerRepo;
import com.examly.springapp.repository.TeamRepo;
import com.examly.springapp.service.OrganizerService;

@Service
public class OrganizerServiceImpl{
//!Constructor based injection
    private final PlayerRepo playerRepo;
    private final TeamRepo teamRepo;
    public OrganizerServiceImpl(PlayerRepo playerRepo,TeamRepo teamRepo){
        this.playerRepo=playerRepo;
        this.teamRepo=teamRepo;
    }
//!================== Retriving all Players who are unsold ===================
    @Override
    public List<Player> getUnsoldPlayers() {
        return playerRepo.getUnsoldPlayers();
    }
//!================== Retriving all Players who are sold ===================
    @Override
    public List<Player> getSoldPlayers() {
        return playerRepo.getSoldPlayers();
    }
//!================== Assigning the player with team using team id and player id ===================
    @Override
    public double assignPlayerToTeam(Long playerId, Long teamId) {
        Player player=playerRepo.findById(playerId).orElse(null);
        Team team=teamRepo.findById(teamId).orElse(null);
        if(player==null||team==null)return (Double) null;
        if(player.getTeam()!=null) {
            throw new PlayerAlreadyAssignedException("Player already assigned");
        }
        double totalExpense=0.0;
        for(Player p:team.getPlayers()){
            totalExpense+=p.getBiddingPrice();
        }
        double result=team.getMaximumBudget()-totalExpense;
        if(team.getMaximumBudget()-totalExpense<player.getBiddingPrice()){
            throw new ExceedsTeamBudgetException("Budget is exceeding");
        }
        player.setTeam(team);
        player.setSold(true);
        playerRepo.save(player);
        return result - player.getBiddingPrice();
    }
//!================== Updating player using player id (sold to unsold) ===================
    @Override
    public void releasePlayerFromTeam(Long playerId) {
        Player player=playerRepo.findById(playerId).orElse(null);
        if(player!=null){
            player.setSold(false);
            player.setTeam(null);
            playerRepo.save(player);
        }
    }
//!================== Retriving all Players with team id ===================
    @Override
    public List<Player> getPlayerListByTeamId(Long teamId) {
        Team team=teamRepo.findById(teamId).orElse(null);
        if(team==null)return null;
        return team.getPlayers();
    }

//!================== Retriving all Teams ===================
    public List<Team> getAllTeams(){
        return teamRepo.findAll();
    }

//================== Retriving Players by category ===================
    public List<Player> getDataByCategory(String category) {
        if(category.equals("All")){
            return playerRepo.findAll();
        }else{
            return playerRepo.findByCategory(category);
        }
    } 
}
