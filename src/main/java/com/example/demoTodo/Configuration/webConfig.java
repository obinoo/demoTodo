package com.example.demoTodo.Configuration;


import com.example.demoTodo.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration

public class webConfig extends WebSecurityConfigurerAdapter {

@Autowired
 CustomUserDetailsService customUserDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(customUserDetailsService);
        auth.setPasswordEncoder(bCryptPasswordEncoder());
        return auth;
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService);
        auth.authenticationProvider(authenticationProvider());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                csrf().disable().authorizeRequests().
                mvcMatchers("/login",
                        "/js/**",
                        "/css/**").permitAll().
                mvcMatchers("/register").permitAll().
                mvcMatchers("/logout").permitAll().
                anyRequest().authenticated()
                .and().formLogin().loginPage("/login").defaultSuccessUrl("/index")
                .failureUrl("/login?error=true").permitAll().and().
                logout().invalidateHttpSession(true).
                clearAuthentication(true).
                logoutSuccessUrl("/login").
                permitAll().and().httpBasic();
    }

}
