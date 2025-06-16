package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    //!Custom query for getting user by username
    @Query("select u from User u where username=?1")
    User findByUsername(String username);

}
