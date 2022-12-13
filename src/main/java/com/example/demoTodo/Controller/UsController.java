package com.example.demoTodo.Controller;

import com.example.demoTodo.Model.Tasks;
import com.example.demoTodo.Model.Us;
import com.example.demoTodo.Repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;



@Controller
public class UsController {


@Autowired
 private UsersRepository userRepository;



    @GetMapping("/register")
    public ModelAndView showRegistrationForm(Model model) {
        model.addAttribute("user", new Us());

        return new ModelAndView("register");
    }


    @PostMapping("/register")
    public String processRegister(Us us) {
        ModelAndView modelAndView = new ModelAndView();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(us.getPassword());
        us.setPassword(encodedPassword);
        modelAndView.addObject("user", new Us());
        userRepository.save(us);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public ModelAndView showLogin(HttpSession session) {

        if (!session.isNew()){
            session.invalidate();
        }
        return new ModelAndView("login.html");
    }


    //Check for Credentials
    @PostMapping("/login")
    public ModelAndView login(@Valid @ModelAttribute(name="loginForm") Us us, Model m, Errors errors,
                              Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView();
        authentication.getName();
        String password = us.getPassword();
        us.setEmail(us.getEmail());
       if (errors.hasErrors()){
           m.addAttribute("error", "Incorrect Email & Password");
           modelAndView.setViewName("login");
       }
      // userRepository.save(us);
       modelAndView.setViewName("index");
        return modelAndView;
    }

    //Logout
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }
}















//import com.example5.project2.Model.User;
//import com.example5.project2.Service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserService userService;
//
//    @GetMapping("/login")
//    private User getCurrentUser(User user) {
//        System.out.println("GET User by username and password *****");
//        return userService.getUser(user);
//    }
//
//    @GetMapping("/login/{username}/{password}")
//    private boolean findUserByUsername(@PathVariable String username, @PathVariable String password) {
//        System.out.println("GET User by username and password *****");
//        return userService.getUserByUsername(username, password);
//    }
//
//    @PostMapping("/createUser")
//    private boolean addUser(User user) {
//        boolean user_exits = userService.findUserByUsername(user.getUsername());
//        if(user_exits) {
//            System.out.println("CANT CREATE USER!");
//            return false;
//        }
//        userService.saveUser(user);
//        return true;
//    }
//}