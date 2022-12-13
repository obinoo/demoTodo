package com.example.demoTodo.Service;

//import com.example.demoTodo.CustomUserDetails;
import com.example.demoTodo.Model.Us;
import com.example.demoTodo.Repos.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	 UsersRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Us us = userRepo.findByEmail(email);
		if (us == null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserDetails userDetails = org.springframework.security.core.userdetails.User.withUsername(
				us.getEmail()).password(us.getPassword()).roles("USER").build();

		return userDetails;
	}

}
