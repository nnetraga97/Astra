package com.nikhil.astra.repository;

import java.util.List;

import com.nikhil.astra.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByUserName(String userName);

    
    
}
