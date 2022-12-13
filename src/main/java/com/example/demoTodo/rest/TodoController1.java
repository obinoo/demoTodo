package com.example.demoTodo.rest;

import com.example.demoTodo.Model.Tasks;
import com.example.demoTodo.Repos.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.util.List;

@RestController
@Validated
@RequestMapping(value = "/project")
public class TodoController1 {

    @Autowired
    TaskRepository todosRepository;

    @GetMapping("/todo")
    public List <Tasks> getall(){
        return  todosRepository.findAll();
    }

    @GetMapping("/todo/{id}")
    public Tasks findbyId(@PathVariable Long id){
        return todosRepository.findById(id).get();
    }

    @PostMapping("/addList")
    public ResponseEntity <Tasks> todolist(@Valid @NotBlank @RequestBody Tasks todos) {
        todos = todosRepository.save(todos);
        return ResponseEntity.status(200).body(todos);
    }

    @PutMapping("/todo/{id}/update")
    public Tasks update(@PathVariable Long id, @RequestBody Tasks todos){
//        todoRepository.findById(todo.getId());
//        todo.setTitle(todo.getTitle());
//        todo.setId(todo.getId());
//        todo.setCompleted(todo.getCompleted());
        return  todosRepository.save(todos);
        // return ResponseEntity
        //       .ok(HttpStatus.CREATED);
    }
    @DeleteMapping("/todo/{id}/")
    public void delete(@PathVariable Long id){
        todosRepository.deleteById(id);

    }
    @GetMapping("/principal")
    public Principal retrievePrincipal(Principal principal) {
        return principal;
    }
    }

