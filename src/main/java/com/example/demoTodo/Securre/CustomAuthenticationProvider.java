//package com.example.demoTodo.Securre;
//
//import com.example.demoTodo.Model.Us;
//import com.example.demoTodo.Repos.TaskRepository;
//import com.example.demoTodo.Repos.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.util.ArrayList;
//
//@Component
//public class CustomAuthenticationProvider
//            implements AuthenticationProvider {
//
////        @Resource
////        UserDetailsService userDetailsService;
//
//    @Autowired
//    TaskRepository taskRepository;
//
//        @Autowired
//        UsersRepository usRepository;
//
//        @Autowired(required = false)
//        private PasswordEncoder passwordEncoder;
//
//
//        @Override
//        public Authentication authenticate(Authentication authentication)
//                throws AuthenticationException {
//            String username = authentication.getName();
//            String password = authentication.getCredentials().toString();
//            Us user1 =usRepository.findByUsername(username);
//            if (null != user1 && user1.getId()>0 && passwordEncoder.matches
//                    (password, user1.getPassword())) {
//                return new UsernamePasswordAuthenticationToken(
//                       username, password, new ArrayList<>());
//            } else {
//                throw new BadCredentialsException("Invalid credentials!");
//            }
//        }
//
//        @Override
//        public boolean supports(Class<?> authentication) {
//            return authentication.equals(UsernamePasswordAuthenticationToken.class);
//        }
//    }
//
