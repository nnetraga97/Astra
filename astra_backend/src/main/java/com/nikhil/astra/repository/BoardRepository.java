package com.nikhil.astra.repository;

import java.util.List;

import com.nikhil.astra.model.Board;
import com.nikhil.astra.model.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BoardRepository extends CrudRepository<Board, Long> {

  Board findById(long id);

  @Query("select users from Board b where b.id = ?1")
  List<Long> getAllUsersbyId(long id);
}