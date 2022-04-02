package com.nikhil.astra.model;


import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;



@Entity
@Table(name = "dashboards")
public class DashBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "data")
    private String data;
    
    @ManyToMany
    private List<User> userList;

    @OneToMany(mappedBy = "board")
    private List<DashBoardTransactionHistory> transaction_details;

    
   
    @Column(name = "dTofCreation")
    private LocalDateTime dTofCreation;

    public DashBoard(User createdUser) {
        this.userList = new ArrayList<>();
        userList.add(createdUser);
        dTofCreation =  LocalDateTime.now();
        this.transaction_details = new ArrayList<>();

        transaction_details.add(new DashBoardTransactionHistory(createdUser));
    
    }

    protected DashBoard() {

    }
    public LocalDateTime getDTofCreation() {
        return this.dTofCreation;
    }

    public void setDTofCreation(LocalDateTime dTofCreation) {
        this.dTofCreation = dTofCreation;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DashBoardTransactionHistory> getTransaction_details() {
        return this.transaction_details;
    }

    public void setTransaction_details(List<DashBoardTransactionHistory> transaction_details) {
        this.transaction_details = transaction_details;
    }


}
