package com.nikhil.astra.repository;

import java.util.List;

import com.nikhil.astra.model.Board;
import com.nikhil.astra.model.User;
import com.nikhil.astra.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

  User findById(long id);

  @Query("select boardList from User u where u.id =?1")
  List<Long> findBoardListbyId(long id);

  @Query("select currentBoard from User u where u.id =?1")
  Long findCurrentBoardById(long id);

  List<User> findByUserName(String userName);


}