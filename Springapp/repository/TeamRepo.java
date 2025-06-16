package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.Team;

@Repository
public interface TeamRepo extends JpaRepository<Team,Long>{
   @Query("select team from Team team where team.name=?1")
   Team getTeamByName(String name);

   @Query("select team from Team team where team.id=?1")
   Team getTeamById(long id);
}
