package com.nikhil.astra.repository;

import java.util.List;

import com.nikhil.astra.model.DashBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;



@Repository
public interface DashBoardRepository extends JpaRepository<DashBoard,Long>{

    DashBoard findById(long id);
    
    
    
}
