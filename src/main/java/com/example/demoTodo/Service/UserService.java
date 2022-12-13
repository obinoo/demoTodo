package com.example.demoTodo.Service;

import com.example.demoTodo.Model.Us;
import com.example.demoTodo.Repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    public Us findByEmail(String email) {

        return usersRepository.findByEmail(email);
    }


}
