package com.nikhil.astra.WebSocketConfig;
import java.time.LocalDateTime;

public class Message {

    private String userId;

    private String boardid;

    private String data;

    private String create_dt;

    public String getCreate_dt() {
        return this.create_dt;
    }

    public void setCreate_dt(String create_dt) {
        this.create_dt = create_dt;
    }

    public Message(){
        this.create_dt = LocalDateTime.now().toString();
    }

    public Message(String userid, String boardid, String data){
        this.boardid = boardid;
        this.userId = userid;
        this.data = data;
        this.create_dt = LocalDateTime.now().toString();
    }
    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBoardid() {
        return this.boardid;
    }

    public void setBoardid(String boardid) {
        this.boardid = boardid;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    
}
