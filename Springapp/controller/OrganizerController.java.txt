package com.examly.springapp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Player;
import com.examly.springapp.service.serviceimpl.OrganizerServiceImpl;

@RestController
@RequestMapping("api/organizer")
public class OrganizerController {
//!Constructor based injection
    private OrganizerServiceImpl service;
    public OrganizerController(OrganizerServiceImpl organizerService){
        this.service=organizerService;
    }

//!================== Assigning the player with team using team id and player id ===================
    @PostMapping("/assign-player")
    public ResponseEntity<?> assignPlayerToTeam(@RequestParam Long teamId, @RequestParam Long playerId){
        double result=service.assignPlayerToTeam(playerId,teamId);
        if(result >= 0.0){
            return ResponseEntity.status(201).body(result);
        } else {
            return ResponseEntity.status(404).body("Player not assigned.");
        }
    }

//!================== Retriving all Players who are sold ===================
    @GetMapping("/sold-players")
    public ResponseEntity<?> getSoldPlayers(){
        return ResponseEntity.status(200).body(service.getSoldPlayers());
    }

//!================== Retriving all Players who are unsold ===================
    @GetMapping("/unsold-players")
    public ResponseEntity<?> getUnsoldPlayers(){
        return ResponseEntity.status(200).body(service.getUnsoldPlayers());
    }

//!================== Retriving all Players with team id ===================
    @GetMapping("/player-list/{teamId}")
    public ResponseEntity<?> getPlayerListByTeamId(@PathVariable Long teamId){
        return ResponseEntity.status(200).body(service.getPlayerListByTeamId(teamId));
    }

//!================== Updating player using player id (sold or unsold) ===================
    @PutMapping("/release-player/{playerId}")
    public ResponseEntity<?> releasePlayerFromTeam(@PathVariable Long playerId){
        service.releasePlayerFromTeam(playerId);
        return ResponseEntity.status(200).body(true);
    }
//!================== Retriving all Teams ===================
    @GetMapping("/team-list")
    public ResponseEntity<?> getAllTeams(){
        return ResponseEntity.status(200).body(service.getAllTeams());
    }

//!================== Retriving Players by category ===================
    @GetMapping("/player-list/category/{category}")
    public ResponseEntity<List<Player>> categoryPlayerList(@PathVariable String category){
        List<Player> list = service.getDataByCategory(category);
        return ResponseEntity.status(200).body(list);
    }
}
