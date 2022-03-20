package com.nikhil.astra.WebSocketConfig;

import com.google.gson.Gson;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputParser {
    private static final Logger logger = LoggerFactory.getLogger(InputParser.class);
    
    private String data;

    private Message decodedMessage;

    public InputParser(String data){
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Message getDecodedMessage() {
        return this.decodedMessage;
    }

    public void setDecodedMessage(Message decodedMessage) {
        this.decodedMessage = decodedMessage;
    }

    public Message parseInput(){
        Message decoded = new Message();
        logger.info(data);
        Gson gson = new Gson();
        try{
        decoded = gson.fromJson(data, Message.class);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return decoded;
    }

}
