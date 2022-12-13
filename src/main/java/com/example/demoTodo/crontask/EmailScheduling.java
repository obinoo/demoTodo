package com.example.demoTodo.crontask;

import com.example.demoTodo.Model.Tasks;
import com.example.demoTodo.Model.Us;
import com.example.demoTodo.Repos.TaskRepository;
import com.example.demoTodo.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Component
@EnableScheduling
public class EmailScheduling {

    @Autowired
    private EmailService emailService;

    @Autowired
    private TaskRepository taskRepository;



    @Scheduled(cron = "0 59 23 * * *")//11:59pm EveryDay
    public  void emailDueTomorrow() throws MessagingException {
         //  LocalDate date  = LocalDate.now();
        List<Tasks> items = taskRepository.findAllByDueDate(LocalDate.now().plusDays(1));

        for (Tasks tasks : items){
            emailService.sendSimpleEmail(tasks);
        }
        }

        @Scheduled(cron ="0 00 12 * * *")//12:00 Today
        public void emailDueToday() throws MessagingException{
        List<Tasks> item = taskRepository.findAllByDueDate(LocalDate.now());
        for (Tasks tasks : item){
            emailService.sendSimpleEmail(tasks);
        }
        }
    }

