package com.examly.springapp.service.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entity.Team;
import com.examly.springapp.repository.TeamRepo;
import com.examly.springapp.service.TeamService;
@Service
public class TeamServiceImpl{
//!Constructor based injection
    private final TeamRepo teamRepo;
    public TeamServiceImpl(TeamRepo teamRepo){
        this.teamRepo=teamRepo;
    }
//!================== Adding team ===================
    public Team addTeam(Team team){
        team=teamRepo.save(team);
        return team;
    }
//!================== Updating the team with team idc===================
    public Team updateTeam(long teamId,Team team) {
        // teamRepo.deleteById(teamId);
        team.setId(teamId);
        team=teamRepo.save(team);
        return team;
    }
//!================== Retriving all teams ==================
    public List<Team> getAllTeams() {
        return teamRepo.findAll();
    }
//!================== Retrieving the team with team id ==================
    public Team getTeamById(long teamId) {
        return teamRepo.findById(teamId).orElse(null);
    }
//!================== Deleting the team with team id ==================
    public boolean deleteTeamById(long teamId) {
        if(teamRepo.existsById(teamId)){
            teamRepo.deleteById(teamId);
            return true;
        }
        return false; 
    }
}
