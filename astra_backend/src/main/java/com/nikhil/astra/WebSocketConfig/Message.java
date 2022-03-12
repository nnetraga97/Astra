package com.nikhil.astra.WebSocketConfig;

public class Message {

     private String boardid;

    private String userid;

    private String data;

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

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Message(String boardid,String userid,String data){
        this.boardid = boardid;
        this.userid = userid;
        this.data = data;

    }
    
}
