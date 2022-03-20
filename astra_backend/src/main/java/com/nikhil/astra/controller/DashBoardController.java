package com.nikhil.astra.controller;

import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import com.nikhil.astra.WebSocketConfig.Message;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.User;

@Controller
public class DashBoardController {
    
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private DashBoardRepository dashBoardRepository;
	@MessageMapping("/askdata")
	@SendTo("/receivedata/")
	public Message getBoardData(Message request){
		String currBoardId = request.getBoardid();
		DashBoard currBoard = dashBoardRepository.findById(Long.parseLong(currBoardId));
		currBoard.setData(request.getData());
		currBoard.getActions().add("board updated by: "+request.getUserId());
		Message response = request;
		try {
			dashBoardRepository.save(currBoard);
			response.setData("Data updated");
			return response;
		} catch (Exception e) {
			response.setData("Data update failed----"+response.getData());
		}
		return response;
	}


   
    

}
