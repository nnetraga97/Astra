package com.nikhil.astra.controller;

import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.User;

@Controller
public class DashBoardController {
    
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private DashBoardRepository dashBoardRepository;
    @MessageMapping("/board/{boardid}/{userId}")
    @SendTo("board/{boardid}/")
    public ResponseEntity<String> getData(@PathVariable long boardId,@PathVariable long userId){
        try{
            DashBoard currentBoard = dashBoardRepository.findById(boardId);
            currentBoard.getActions().add("got data:"+boardId+" by user:"+userId);
            dashBoardRepository.save(currentBoard);
            return ResponseEntity.ok("data changed");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("data not changed");
        }
    }

    @MessageMapping("/board/update/{boardId}/{data}/{userId}")
    @SendTo("board/update/{boardid}/")
    public ResponseEntity<String> getData(@PathVariable long boardId,@PathVariable String data,@PathVariable long userId){
        try{
            DashBoard currentBoard = dashBoardRepository.findById(boardId);
            currentBoard.setData(data);
            currentBoard.getActions().add("changed data to board:"+boardId+" by user:"+userId);
            dashBoardRepository.save(currentBoard);
            return ResponseEntity.ok(currentBoard.getData());
        }
        catch(Exception e){
            e.printStackTrace();
            long boardid = boardId;
            return ResponseEntity.ok("data not changed");
        }
    }

}
