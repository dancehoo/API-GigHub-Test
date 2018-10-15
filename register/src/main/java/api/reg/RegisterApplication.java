package api.reg;

import java.util.Date;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class RegisterApplication {

	private static final Logger logger = LogManager.getLogger(RegisterApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(RegisterApplication.class, args);
		logger.info("APP STARTED :"+new Date());
	}
}
