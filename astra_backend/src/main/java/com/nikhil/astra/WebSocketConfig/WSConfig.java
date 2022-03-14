package com.nikhil.astra.WebSocketConfig;
import com.corundumstudio.socketio.listener.*;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.repository.DashBoardRepository;

import org.springframework.beans.factory.annotation.Autowired;

import antlr.collections.List;

import java.util.ArrayList;

import javax.sql.ConnectionEvent;
import javax.sql.ConnectionEventListener;

import com.corundumstudio.socketio.*;
public class WSConfig {
    
    @Autowired
	private DashBoardRepository dashBoardRepository;

    public WSConfig(){
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(8083);
        config.setMaxFramePayloadLength(1024*1024);
        config.setMaxHttpContentLength(1024 * 1024);

        final SocketIOServer server = new SocketIOServer(config);

        server.addEventListener("update-board-data", Message.class, new DataListener<Message>() {

            @Override
            public void onData(SocketIOClient client, Message message, AckRequest ackRequest) throws Exception {
                try{
                    System.out.print("update event received!!!");
                    System.out.print("Message:["+message.getBoardid()+","+message.getUserid()+""+message.getData());
                    DashBoard currentBoard = dashBoardRepository.findById(Long.parseLong(message.getBoardid()));
                    if(currentBoard==null){
                        System.out.print("no board found!!!");
                        server.getBroadcastOperations().sendEvent("send-board-data", "no board found");
                    }
                    else{
                        currentBoard.setData(message.getData());
                        dashBoardRepository.save(currentBoard);
                        server.getBroadcastOperations().sendEvent("send-board-data", "board updated in db");
                        System.out.print("board updated");
                    }
                }
                catch(Exception e){
                    server.getBroadcastOperations().sendEvent("send-board-data", "ERR");
                }
            }
            
        });

        server.addEventListener("get-board-data", Message.class, new DataListener<Message>() {

            @Override
            public void onData(SocketIOClient client, Message message, AckRequest ackRequest) throws Exception {
                try{
                    DashBoard currentBoard = dashBoardRepository.findById(Long.parseLong(message.getBoardid()));
                    if(currentBoard==null){
                        server.getBroadcastOperations().sendEvent("receive-board-data", "no board found");
                    }
                    else{
                        
                        server.getBroadcastOperations().sendEvent("receive-board-data", currentBoard.getData());
                    }
                }
                catch(Exception e){
                    server.getBroadcastOperations().sendEvent("receive-board-data", "ERR");
                }
            }
            
        });

        server.addConnectListener(client -> System.out.println(client.getSessionId()+" Connected!"));

        server.start();


    }
    
}
