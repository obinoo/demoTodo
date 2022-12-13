package com.example.demoTodo.Controller;


import com.example.demoTodo.Model.Tasks;
import com.example.demoTodo.Model.Us;
import com.example.demoTodo.Repos.TaskRepository;
import com.example.demoTodo.Repos.UsersRepository;
import com.example.demoTodo.Service.UserService;
import org.hibernate.type.DateType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@Validated
public class TaskController {
    private static final Logger log = LoggerFactory.getLogger("TodosController.class");

    @Autowired
    TaskRepository todosRepository;

    @Autowired
    UserService userService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date - dd/MM/yyyy
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
       // StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }

    @GetMapping("/index")
    public String index(Model model){
        String name = getLoggedInUserName(model);
        Us us = userService.findByEmail(name);
      List<Tasks> todoItems = todosRepository.findAllByEmail(us.getEmail());
        model.addAttribute("todoItems", todoItems);
        return "index";
    }

    private String getLoggedInUserName(Model model){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            return ((UserDetails) principal).getUsername();
        }
        return principal.toString();
    }

    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        Tasks todoItem = todosRepository
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("TodoItem id: " + id + " not found"));

        model.addAttribute("todo", todoItem);
        return "update";
    }

    @PostMapping("/todo/{id}")
    public String updateTodoItem(@PathVariable("id") long id,
                                 @Valid Tasks todoItem, BindingResult result, Model model) {
        if (result.hasErrors()) {
            todoItem.setId(id);
            return "update";
        }

        todoItem.setEmail(getLoggedInUserName(model));
        todosRepository.save(todoItem);
        return "redirect:/index";
    }


    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id, @Valid Model model) {
        Tasks red = todosRepository.findById(id).get();
        todosRepository.delete(red);
        return "redirect:/index";
    }

        @GetMapping("/create-todo")
        public ModelAndView create(@ModelAttribute("todo") Tasks tasks){
            ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("add-todo");
       return modelAndView;

    }

    
    @PostMapping("/todo")
    public String createTodo(@Valid @ModelAttribute("todo") Tasks todos, Errors errors, Model model){
        ModelAndView modelAndView = new ModelAndView();
        if (errors.hasErrors()){
           modelAndView.addObject("errors", errors);
           return "add-todo.html";
        }
        todos.setEmail(getLoggedInUserName(model));
        todos.setCreatedDate(Instant.now());
        todosRepository.findAllByDueDate(todos.getDueDate());
          todosRepository.save(todos);
        return "redirect:/index";
    }

}
