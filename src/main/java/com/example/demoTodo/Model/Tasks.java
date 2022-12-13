package com.example.demoTodo.Model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo_items")
public class Tasks {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)

        private Long id;

        @NotBlank(message = "Description is required")
        @Size(min = 3 , message= "Must be at least three characters")
        private String description;

        private boolean complete;


        private Instant createdDate;


        @DateTimeFormat(pattern ="yyyy-MM-dd")
        private LocalDate dueDate;

        @Getter
        @Setter
        @Email
        private String email;

        @ManyToOne
         //       @JoinColumn(name = "US_ID")
        private Us us ;
}



