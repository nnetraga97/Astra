package com.nikhil.astra.controller;

import java.util.Optional;

import com.nikhil.astra.model.Board;
import com.nikhil.astra.repository.BoardRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class BoardController {
  @Autowired
  BoardRepository br;

  @MessageMapping("/board")
  @SendTo("/board/update")
  public Boolean BoardData(Long id, String updateddata) throws Exception { // simulated delay
   
    Optional<Board> currboard = br.findById(id);
    if(currboard==null){
        return false;
    }
    else{
        try{
        currboard.get().setBoardData(updateddata);
        br.save(currboard.get());
        return true;
      }
      catch(Exception e){
        return false;
      }
    }
  }

}