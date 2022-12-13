package com.example.demoTodo;

import com.example.demoTodo.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

import javax.mail.MessagingException;

@SpringBootApplication
@EntityScan("com.example.demoTodo.Model")
@EnableAutoConfiguration
@EnableJpaRepositories("com.example.demoTodo.Repos")
@EnableScheduling
public class DemoTodoApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoTodoApplication.class, args);
	}

//	@EventListener(ApplicationReadyEvent.class)
//	public void triggerMail() throws MessagingException {
//
//		emailService.sendSimpleEmail("nnubiacobinna@gmail.com",
//				"You have a task that is due.",
//				"Due Tasks");
//	}
}
