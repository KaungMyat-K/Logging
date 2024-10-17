package com.test.repo;

import java.util.*;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User,Long>{
    
    Optional<User> findByEmail(String email);
    




}
