package com.nikhil.astra.WebSocketConfig;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.google.gson.Gson;
import com.nikhil.astra.constants.DashBoardConstants;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.DashBoardTransactionHistory;
import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.DashBoardTransactionHistoryRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.SubProtocolCapable;
import org.springframework.web.socket.TextMessage;

public class MyHandler extends TextWebSocketHandler implements SubProtocolCapable {
    private final Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private static final Logger logger = LoggerFactory.getLogger(MyHandler.class);
    @Autowired
	private DashBoardRepository dashBoardRepository;

    @Autowired
	private DashBoardTransactionHistoryRepository dashboardTranRepository;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        logger.info("Server connection opened");
        sessions.add(session);
        TextMessage message = new TextMessage("one-time message from server");
        //logger.info("Server sends: {}", message);
        session.sendMessage(message);
    }
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        logger.info("Server connection closed: {}", status);
        sessions.remove(session);
    }
    //@Scheduled(fixedRate = 10000)
    void sendPeriodicMessages() throws IOException {
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                String broadcast = "server periodic message " + LocalTime.now();
                logger.info("Server sends: {}", broadcast);
                session.sendMessage(new TextMessage(broadcast));
            }
        }
    }
    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
    	
        String request = message.getPayload();
        
        
        	logger.info("Server received: {}", request );
        
        InputParser parser = new InputParser(request);
        Message decodedMessage = null;
        try{
        	decodedMessage= parser.parseInput();
            DashBoard currboard =  dashBoardRepository.findById(Long.parseLong(decodedMessage.getBoardid()));         
            currboard.setData(decodedMessage.getData());
            
            DashBoardTransactionHistory updateTransaction = new DashBoardTransactionHistory(decodedMessage.getUserId(), DashBoardConstants.UPDATE_BOARD, currboard);
            dashboardTranRepository.save(updateTransaction);
            dashBoardRepository.save(currboard);
        }
        catch(Exception e){
          
        }
        logger.info("Server received: {}", request );
        String response = String.format(decodedMessage.getData());
        //logger.info("Server sends: {}", response);
        session.sendMessage(new TextMessage(response));
    }
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) {
        logger.info("Server transport error: {}", exception.getMessage());
    }
    @Override
    public List<String> getSubProtocols() {
        return Collections.singletonList("*");
    }
   
 }