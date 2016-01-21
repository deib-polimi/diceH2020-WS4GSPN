package it.polimi.diceH2020.WS4GSPN.demo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

//This is a Spring boot Application. Spring intelligently handles Context and xml files reducing boilerplate.
//@SpringBootApplication comprehends this annotations:
	//1)@Configuration  --> this is the configuration class, generate bean definitions container and service request for those beans at runtime
	//2)@EnableAutoConfiguration --> trying to configure application "guessing"
	//3)@ComponentScan --> by default it search beans only in this class package. I'll override it.
//Make Jar not War ;) But we need to export this application in a war to deploy it in a standard Web Server.
@SpringBootApplication
@EnableAsync  //required in the application class to instruct Spring to look for method annotated as asynch tasks
@EntityScan({"it.polimi.diceH2020.WS4GSPN.model"})
@EnableJpaRepositories("it.polimi.diceH2020.WS4GSPN.repository")
@ComponentScan({"it.polimi.diceH2020.WS4GSPN.demo","it.polimi.diceH2020.WS4GSPN.controller","it.polimi.diceH2020.WS4GSPN.service","it.polimi.diceH2020.WS4GSPN.utility","it.polimi.diceH2020.WS4GSPN.model"})
public class DiceApplication {
	
    public static void main(String[] args) {
        SpringApplication.run(DiceApplication.class, args);
    }
}
