package com.nikhil.astra.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    @Column(name = "createdUserID")
    private long createdUserId;
    @Column(name = "dTofCreation")
    private String dTofCreation;
    @Column(name = "actions")
    private ArrayList<String> actions;

    public DashBoard(long createdUserId) {
        this.createdUserId = createdUserId;
        dTofCreation = Long.toString(System.currentTimeMillis());

    }

    protected DashBoard() {

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

    public long getCreatedUserID() {
        return this.createdUserId;
    }

    public void setCreatedUserID(long createdUserID) {
        this.createdUserId = createdUserID;
    }

    public String getDTofCreation() {
        return this.dTofCreation;
    }

    public void setDTofCreation(String dTofCreation) {
        this.dTofCreation = dTofCreation;
    }

    public List<String> getActions() {
        return this.actions;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setActions(ArrayList<String> actions) {
        this.actions = actions;
    }

}
