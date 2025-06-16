package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.examly.springapp.entity.Player;

public interface PlayerRepo extends JpaRepository<Player,Long> {
    //!Custom query for getting the list of players who are sold
    @Query("select player from Player player where sold=true")
    List<Player> getSoldPlayers();

    // List<Player> findBySoldTrue();
    List<Player> findByCategory(String category);

    
    //!Custom query for getting the list of players who are unsold
    @Query("select player from Player player where sold=false")
    List<Player> getUnsoldPlayers(); 
}
