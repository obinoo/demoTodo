package com.example.demoTodo.Repos;


import com.example.demoTodo.Model.Tasks;
import com.example.demoTodo.Model.Us;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Tasks, Long> {

     List <Tasks> findAllByEmail(String email);

     List<Tasks> findAllByDueDate
             (LocalDate dueDate);
}


