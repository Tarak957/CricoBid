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

import com.examly.springapp.entity.Player;
import com.examly.springapp.service.serviceimpl.PlayerServiceImpl;

@RestController
@RequestMapping("/api/player")
public class PlayerController {
//!Constructor based injection
    private final PlayerServiceImpl service;
    public PlayerController(PlayerServiceImpl service){
        this.service=service;
    }
//!================== Adding player ===================
    @PostMapping
    public ResponseEntity<Player> addPlayer(@RequestBody Player player){
        return ResponseEntity.status(200).body(service.addPlayer(player));
    } 
//!================== Updating player using player id ===================
    @PutMapping("/{playerId}")
    public ResponseEntity<Player> updatePlayer(@PathVariable int playerId,@RequestBody Player player){
        return ResponseEntity.status(200).body(service.updatePlayer(playerId,player));
    }
//!================== Retriving all Players ===================
    @GetMapping
    public ResponseEntity<List<Player>> getPlayer(){
        return ResponseEntity.status(200).body(service.getPlayer());
    }
//!================== Retrieving the player with player id ===================
    @GetMapping("/{playerId}")
    public ResponseEntity<Player> getPlayerById(@PathVariable int playerId){
        return ResponseEntity.status(200).body(service.getPlayerById(playerId));
    }
//!================== Deleting the player with player id ===================
    @DeleteMapping("/{playerId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable int playerId){
        return ResponseEntity.status(200).body(service.deleteById(playerId));
    }
}

