package com.nikhil.astra;



import com.nikhil.astra.model.Board;
import com.nikhil.astra.model.User;
import com.nikhil.astra.repository.BoardRepository;
import com.nikhil.astra.repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AstraApplication {

	private static final Logger log = LoggerFactory.getLogger(AstraApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(AstraApplication.class, args);
	}

	@Bean
  public CommandLineRunner demo(UserRepository urepository,BoardRepository brepository) {
    return (args) -> {
      // save a few Users
	  User nikhil = new User("Nikhil");
	  User astra = new User("Astra");
      urepository.save(nikhil);
      urepository.save(astra);

	  //save a few boards
	  Board board1 = new Board(nikhil);
	  Board board2 = new Board(astra);
	  board1.addUser(nikhil);
	  brepository.save(board1);
      brepository.save(board2);

      // fetch all Users
      log.info("Users found with findAll():");
      log.info("-------------------------------");
      for (User User : urepository.findAll()) {
        log.info(User.toString());
      }
      log.info("");

	   // fetch all Boards
	   log.info("Boards found with findAll():");
	   log.info("-------------------------------");
	   for (Board board : brepository.findAll()) {
		 log.info("boardid - "+board.getId()+" users:"+board.getUsers().size()); 
	   }
	   log.info("");

      // fetch an individual User by ID
      User User = urepository.findById(1L);
      log.info("User found with findById(1L):");
      log.info("--------------------------------");
      log.info(User.toString());
      log.info("");

      // fetch Users by last name
      log.info("User found with findByLastName('Nikhil'):");
      log.info("--------------------------------------------");
      urepository.findByUserName("Nikhil").forEach(bauer -> {
        log.info(bauer.toString());
      });
      // for (User bauer : repository.findByLastName("Bauer")) {
      //  log.info(bauer.toString());
      // }
      log.info("");
    };
  }

}
