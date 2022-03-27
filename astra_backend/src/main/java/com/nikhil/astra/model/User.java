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



@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy=GenerationType.AUTO)
  private Long id;
  @Column(name = "username")
  private String userName;
  @Column(name = "password")
  private String password;

  @Column(name = "boardList")
  @ManyToMany
  private List<DashBoard> boardList;

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



  public List<DashBoard> getboardList() {
      return this.boardList;
  }

  public void setboardList(ArrayList<DashBoard> boardList) {
      this.boardList = boardList;
  }

 
  
@Override
public String toString() {
  return String.format(
      "User[id=%d, userName='%s']",
      id, userName);
}

public Long getId() {
  return id;
}

public String getPassword() {
  return this.password;
}

public void setPassword(String password) {
  this.password = password;
}




}