package com.examly.springapp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Player;
import com.examly.springapp.repository.PlayerRepo;

@Service
public class PlayerServiceImpl {
//!Constructor based injection
    private final PlayerRepo playerRepo;
    public PlayerServiceImpl(PlayerRepo playerRepo){
        this.playerRepo=playerRepo;
    }
//!================== Adding player ===================
    public Player addPlayer(Player player) {
        return playerRepo.save(player);
    }
//!================== Retriving all Players ===================
    public List<Player> getPlayer() {
        return playerRepo.findAll();
    }
//!================== Updating player using player id ===================
    public Player updatePlayer(long playerId,Player player) {
        player.setId(playerId);
        return playerRepo.save(player);
    }
//!================== Retrieving the player with player id ===================
    public Player getPlayerById(long playerId) {
        return playerRepo.findById(playerId).orElse(null);
    }
//!================== Deleting the player with player id ===================
    public Boolean deleteById(long playerId) {
        if(playerRepo.existsById(playerId)){
         playerRepo.deleteById(playerId);
         return true;
        }
        return false;
    }
}
