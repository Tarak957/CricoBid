package com.examly.springapp.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.entity.Team;

@RestController
public class TestController {
    @GetMapping("/api/test/welcome")
    public String welcome(){
        return "Welcome to Spring Boot Project";
    }
    @GetMapping("api/test/team")
    public List<Team> getTeam(){
        List<Team> teamList=new ArrayList<>();
        Team team=new Team(1,"Team 1", 10000);
        teamList.add(team);
        return teamList;
    }
}
