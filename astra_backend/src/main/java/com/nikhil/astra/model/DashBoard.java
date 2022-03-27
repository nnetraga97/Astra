package com.nikhil.astra.model;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

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
   
    @Column(name = "dTofCreation")
    private LocalDateTime dTofCreation;
    @Column(name = "actions")
    private ArrayList<String> actions;

    @Column(name = "code")
    @GeneratedValue
    private UUID codeString;

    public DashBoard(User createdUser) {
        this.userList = new ArrayList<>();
        userList.add(createdUser);
        dTofCreation =  LocalDateTime.now();
        actions = new ArrayList<>();    
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
