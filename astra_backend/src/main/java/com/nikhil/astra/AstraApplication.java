package com.nikhil.astra;




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

}
