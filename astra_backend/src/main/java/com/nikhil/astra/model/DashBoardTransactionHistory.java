package com.nikhil.astra.model;

import java.time.LocalDateTime;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.nikhil.astra.constants.DashBoardConstants;

@Entity
@Table(name = "DashBoardTransactionHistory")
public class DashBoardTransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long trasanction_id;

    @Column(name = "dTofCreation")
    private LocalDateTime dTofCreation;


    @Column(name = "board_id")
    private Long board;


    @Column(name = "transaction_cd")
    private String transaction_cd;


    @Column(name = "transaction_txt")
    private String transaction_txt;

    @Column(name = "update_Userid")
    private Long update_User;


    protected DashBoardTransactionHistory(){
        
    }
    public DashBoardTransactionHistory(User updateUser) {
         dTofCreation = LocalDateTime.now();
        this.update_User = updateUser.getId();
        

    }

    public DashBoardTransactionHistory(String updadeString, String transString, DashBoard board) {
        dTofCreation = LocalDateTime.now();
       this.update_User = Long.parseLong(updadeString);
       this.transaction_cd = transString;
       this.transaction_txt = computeTransactionText(transString);
       this.board = board.getId();
   }


   private String computeTransactionText(String trans_cd){
       String ans = "";
             
                if(trans_cd.equalsIgnoreCase(DashBoardConstants.CREATE_BOARD))
                    ans= DashBoardConstants.CREATE_TXT;
                

                else if(trans_cd.equalsIgnoreCase(DashBoardConstants.ADD_USER))
                    ans= DashBoardConstants.ADD_TXT;
                
                else if(trans_cd.equalsIgnoreCase(DashBoardConstants.DELETE_BOARD))
                    ans=  DashBoardConstants.DELETE_TXT;
               

                else if(trans_cd.equalsIgnoreCase(DashBoardConstants.REMOVE_USER)) 
                    ans=  DashBoardConstants.REMOVE_TXT;
                 

                else if(trans_cd.equalsIgnoreCase(DashBoardConstants.UPDATE_BOARD)) 
                    ans= DashBoardConstants.UPDATE_TXT;

            return ans;
   }

    public Long getUpdate_User() {
        return this.update_User;
    }
    public String getTransaction_txt() {
        return this.transaction_txt;
    }


    public void setTransaction_txt(String transaction_txt) {
        this.transaction_txt = transaction_txt;
    }

    public void setUpdate_User(Long update_User) {
        this.update_User = update_User;
    }

    
    public Long getBoard_id() {
        return this.board;
    }

    public void setBoard_id(Long board_id) {
        this.board = board_id;
    }
    public LocalDateTime getDTofCreation() {
        return this.dTofCreation;
    }

    public void setDTofCreation(LocalDateTime dTofCreation) {
        this.dTofCreation = dTofCreation;
    }


    public Long getId() {
        return this.trasanction_id;
    }

    public void setId(Long id) {
        this.trasanction_id = id;
    }

}
