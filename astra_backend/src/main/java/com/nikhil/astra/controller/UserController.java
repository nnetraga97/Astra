package com.nikhil.astra.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.nikhil.astra.constants.DashBoardConstants;
import com.nikhil.astra.model.DashBoard;
import com.nikhil.astra.model.DashBoardTransactionHistory;
import com.nikhil.astra.model.User;
import com.nikhil.astra.repository.DashBoardRepository;
import com.nikhil.astra.repository.DashBoardTransactionHistoryRepository;
import com.nikhil.astra.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/")
public class UserController {
    @Autowired
	private UserRepository userRepository;
	@Autowired
	private DashBoardRepository dashBoardRepository;

    @Autowired
	private DashBoardTransactionHistoryRepository dashboardTranRepository;

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
            newUser.setboardList(new ArrayList<DashBoard>());
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

    @PostMapping("board/{userIds}/{boardIds}")
    public ResponseEntity<String> deleteBoard(@PathVariable String userIds,@PathVariable String boardIds){
        Long userId = Long.parseLong(userIds);
        Long boardId = Long.parseLong(boardIds);
        Optional<User> dbUser = userRepository.findById(userId);
        if(dbUser!=null){
            User currentUser = dbUser.get();
            List<DashBoard> boardList = currentUser.getboardList();

            for(DashBoard userBoard : boardList){
                if(userBoard.getId()==boardId){
                    currentUser.getboardList().remove(userBoard);
                    try{
                        dashBoardRepository.delete(userBoard);
                        userRepository.save(currentUser);
                        DashBoardTransactionHistory deleteTransaction = new DashBoardTransactionHistory(currentUser, DashBoardConstants.DELETE_BOARD, userBoard);
                        dashboardTranRepository.save(deleteTransaction);
                        return ResponseEntity.ok(Long.toString(currentUser.getId()));
                    }
                    catch(Exception e){
                        return ResponseEntity.ok("unable to delete board");
                    }
                    
                }
              
            }

                return ResponseEntity.ok("not found given board id");
       

        }
        else{
            return ResponseEntity.ok("unable to delete board");
        }
 
       
    }

    @PostMapping("user/createBoard/{userids}/{boardName}")
    public ResponseEntity<String> createDashBoard(@PathVariable String userids,@PathVariable String boardName){
        Long userid = Long.parseLong(userids);
        
        Optional<User> cUser = userRepository.findById(userid);
        if(cUser!=null){
            User currentUser = cUser.get();
            try{
                DashBoard newboard = new DashBoard(currentUser);
                newboard.setData("");              
                newboard.setName(boardName);
                dashBoardRepository.save(newboard);
                currentUser.getboardList().add(newboard);
                userRepository.save(currentUser);
                DashBoardTransactionHistory updateTransaction = new DashBoardTransactionHistory(currentUser, DashBoardConstants.CREATE_BOARD, newboard);
                dashboardTranRepository.save(updateTransaction);
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

    @PostMapping("user/addBoard/{userids}/{boardids}")
    public ResponseEntity<String> addDashBoard(@PathVariable String userids,@PathVariable String boardids){
        Long userid = Long.parseLong(userids);
        Long boardid = Long.parseLong(boardids);

        try{
           Optional<User> dbUser = userRepository.findById(userid);
           if(dbUser!=null){
               User currentUser = dbUser.get();
               Optional<DashBoard> boardtoAdd = dashBoardRepository.findById(boardid);
               if(boardtoAdd!=null){
                   currentUser.getboardList().add(boardtoAdd.get());                 
                   dashBoardRepository.save(boardtoAdd.get());
                   userRepository.save(currentUser);
                   DashBoardTransactionHistory addTransaction = new DashBoardTransactionHistory(currentUser, DashBoardConstants.ADD_USER, boardtoAdd.get());
                   dashboardTranRepository.save(addTransaction);
                   return ResponseEntity.ok("board added");
               }
               else{
                return ResponseEntity.ok("board does not exist");
               }
            }
            else{
                return ResponseEntity.ok("user does not exist");
            } 
            
        }

        catch(Exception e){
            return ResponseEntity.ok("unable to add");
        }
    }

    @PostMapping("user/removeBoard/{userids}/{boardids}")
    public ResponseEntity<String> removeDashBoard(@PathVariable String userids,@PathVariable String boardids){
        Long userid = Long.parseLong(userids);
        Long boardid = Long.parseLong(boardids);

        try{
            Optional<User> dbUser = userRepository.findById(userid);
            if(dbUser!=null){
                User currentUser = dbUser.get();
                DashBoard boardtoAdd = dashBoardRepository.getById(boardid);
                if(boardtoAdd!=null){
                    currentUser.getboardList().remove(boardtoAdd);

                    //boardtoAdd.getActions().add("removed from user "+currentUser.getId()+" list");

                    dashBoardRepository.save(boardtoAdd);
                    userRepository.save(currentUser);
                    DashBoardTransactionHistory removeTransaction = new DashBoardTransactionHistory(currentUser, DashBoardConstants.REMOVE_USER,boardtoAdd);
                   dashboardTranRepository.save(removeTransaction);
                    return ResponseEntity.ok("board removed");
                }
                else{
                 return ResponseEntity.ok("board does not exist");
                }
             }
             else{
                 return ResponseEntity.ok("user does not exist");
             } 
             
         }
 
         catch(Exception e){
             return ResponseEntity.ok("unable to remove");
         }
    }


}
