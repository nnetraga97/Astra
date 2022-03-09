package com.nikhil.astra.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "boards")
public class Board {
    
  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  private ArrayList<Long> users;

  private String boardData;

  public String getBoardData() {
      return this.boardData;
  }

  public void setBoardData(String boardData) {
      this.boardData = boardData;
  }

  protected Board() {

    }

public Board(User user){
    users = new ArrayList<Long>();
    users.add(user.getId());
    boardData = "";
}
  public List<Long> getUsers() {
      return this.users;
  }

  public void setUsers(List<Long> users) {
      this.users = (ArrayList<Long>) users;
  }

  public void addUser(User user){
      this.users.add(user.getId());
  }

public Long getId() {
  return id;
}



}
