package com.example.demo


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.web.client.RestTemplate

@SpringBootApplication
@EnableScheduling
class DemoApplication implements ApplicationRunner, CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
	
	@Value('${database.password}')
	private String dbPass;
	
	static void main(String[] args) {
		logger.info("this is a info message");
		logger.warn("this is a warn message");
		logger.error("this is a error message");
		SpringApplication.run(DemoApplication, args)
	}
	
	@Override
	public void run(ApplicationArguments arg0) throws Exception {
	   System.out.println("Hello World from Application Runner, dbpass: "+dbPass);
	}
	@Override
	public void run(String... arg0) throws Exception {
	   System.out.println("Hello world from Command Line Runner, dbpass: "+dbPass);
	}
}
