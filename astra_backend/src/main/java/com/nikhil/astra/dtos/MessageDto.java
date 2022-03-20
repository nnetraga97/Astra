package com.nikhil.astra.dtos;
public class MessageDto {

   
    private String boardid;
    private String userid;
    private String updatedt;

    
    private String message;
    public MessageDto(String userid, String boardid,String updatedt){
        this.boardid = boardid;
        this.userid = userid;
        this.updatedt = updatedt;
    }
    public String getMessage() {
    
        return message;
    
    }
    
    public void setMessage(String message) {
    
        this.message = message;
    
    }
    public String getBoardid() {
        return this.boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUpdatedt() {
        return this.updatedt;
    }

    public void setUpdatedt(String updatedt) {
        this.updatedt = updatedt;
    }


}