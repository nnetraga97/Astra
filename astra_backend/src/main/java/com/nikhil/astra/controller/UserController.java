package com.nikhil.astra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.User;
import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private DashBoardRepository dashBoardRepository;

    @GetMapping("/users")
	public ResponseEntity<List<User>> getAllUsers(){
		//need to get by user
        List<User> userList = null;
        try{
            userList = userRepository.findAll();
        }
        catch(Exception e){
            e.printStackTrace();
        }
		return ResponseEntity.ok(userList);
	}

    @GetMapping("/user/{username}")
	public User getUser(@PathVariable String username){
		//need to get by user
		return userRepository.findByUserName(username);
	}

    @PostMapping("user/{username}/{password}")
    public ResponseEntity<String> createUser(@PathVariable String username,@PathVariable String password){
        
        if(userRepository.findByUserName(username)==null){
            User newUser = new User(username);
            newUser.setboardList(new ArrayList<Long>());
            newUser.setPassword(password);
            try{
                userRepository.save(newUser);
                return ResponseEntity.ok(Long.toString(newUser.getId()));
            }
            catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.ok("error while saving user");
            }
        }
        else{
            return ResponseEntity.ok("user exists");
        }

    }

    @PostMapping("user/{userid}/{boardId}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long userId,@PathVariable Long boardId){
        Optional<User> dbUser = userRepository.findById(userId);
        if(dbUser!=null){
            User currentUser = dbUser.get();
            List<Long> boardList = currentUser.getboardList();
            if(boardList.contains(boardId)){
                boardList.remove(boardId);
                try{
                    dashBoardRepository.delete(dashBoardRepository.getById(boardId));
                    userRepository.save(currentUser);
                    return ResponseEntity.ok(Long.toString(boardId));
                }
                catch(Exception e){
                    return ResponseEntity.ok("unable to delete board");
                }
            }
            else{
                return ResponseEntity.ok("not found given board id");
            }
        }
        else{
            return ResponseEntity.ok("unable to delete board");
        }
       
    }

    @PostMapping("user/createBoard/{username}/{boardname}")
    public ResponseEntity<String> createDashBoard(@PathVariable long userid,@PathVariable String boardName){
        Optional<User> cUser = userRepository.findById(userid);
        if(cUser!=null){
            User currentUser = cUser.get();
            try{
                DashBoard newboard = new DashBoard(currentUser.getId());
                newboard.setData("");
                newboard.setActions(new ArrayList<String>());
                newboard.getActions().add("board:"+newboard.getId()+" created by user:"+currentUser.getUserName()+
                " with id:"+currentUser.getId());
                newboard.setName(boardName);
                dashBoardRepository.save(newboard);
                currentUser.getboardList().add(newboard.getId());
                userRepository.save(currentUser);
                return ResponseEntity.ok(Long.toString(newboard.getId()));
            }
            catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.ok("unable to create");
            }
        }
        else{
            return ResponseEntity.ok("no user found");
        }
    }



}
