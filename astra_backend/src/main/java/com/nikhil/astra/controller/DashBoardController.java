package com.nikhil.astra.controller;

import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.User;

@Controller
public class DashBoardController {
    
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private DashBoardRepository dashBoardRepository;

	Configuration config;

	


	public void getBoardData(String boardid){

	}
   
    

}
