package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Team;
import com.examly.springapp.service.serviceimpl.TeamServiceImpl;

@RestController
@RequestMapping("/api/team")
public class TeamController {
//!Constructor based injection
    private final TeamServiceImpl service;
    public TeamController(TeamServiceImpl service){
        this.service=service;
    }
//!================== Adding team ===================
    @PostMapping
    public ResponseEntity<Team> addTeam(@RequestBody Team team){
        team=service.addTeam(team);
        return ResponseEntity.status(200).body(team);
    }
//!==================cUpdating the team with team idc===================
    @PutMapping("/{teamId}")
    public ResponseEntity<Team> updateTeam(@PathVariable int teamId,@RequestBody Team team){
        team=service.updateTeam(teamId,team);
        return ResponseEntity.status(200).body(team);
    }
//!================== Retriving all teams ==================
    @GetMapping
    public ResponseEntity<List<Team>> getAllTeams(){
        List<Team> list=service.getAllTeams();
        return ResponseEntity.status(200).body(list);
    }
//!================== Retrieving the team with team id ==================
    @GetMapping("/{teamId}")
    public ResponseEntity<Team> getTeamById(@PathVariable int teamId){
        Team team=service.getTeamById(teamId);
        return ResponseEntity.status(200).body(team);
    }
//!================== Deleting the team with team id ==================
    @DeleteMapping("/{teamId}")
    public ResponseEntity<Boolean> deleteTeamById(@PathVariable int teamId){
        boolean isDeleted=service.deleteTeamById(teamId);
        return ResponseEntity.status(200).body(isDeleted);
    }
}
