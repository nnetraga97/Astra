package com.nikhil.astra.model;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(name = "username")
  private String userName;
  @Column(name = "currentBoard")
  private long currentBoard;
  @Column(name = "boardList")
  private ArrayList<Long> boardList;

  protected User() {}
  public User(String userName) {
    this.userName = userName;
}
  public String getUserName() {
      return this.userName;
  }

  public void setUserName(String userName) {
      this.userName = userName;
  }

  public long getCurrentBoard() {
      return this.currentBoard;
  }

  public void setCurrentBoard(long currentBoard) {
      this.currentBoard = currentBoard;
  }

  public List<Long> getboardList() {
      return this.boardList;
  }

  public void setboardList(ArrayList<Long> boardList) {
      this.boardList = boardList;
  }

 
  
@Override
public String toString() {
  return String.format(
      "User[id=%d, userName='%s', currentBoardId='%s']",
      id, userName, currentBoard);
}

public Long getId() {
  return id;
}

public long getCurrentBoardId() {
  return currentBoard;
}


}