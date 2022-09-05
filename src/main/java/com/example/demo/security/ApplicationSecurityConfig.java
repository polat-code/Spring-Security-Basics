package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.demo.security.ApplicationUserRole.STUDENT;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig  extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                //antMatcher is used to determine permission of access of specific urls such as "/", "/index".
                .antMatchers("/","/index","/css/*","/js/*")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails ozgurhanUser = User.builder()
                .username("ozgurhan.45")
                .password(passwordEncoder.encode("ozgurhan036"))
                .roles(STUDENT.name()) //  The role is ROLES_STUDENT
                .build();

        UserDetails aliUser = User.builder()
                .username("ali05")
                .password(passwordEncoder.encode("ali05"))
                .roles(ApplicationUserRole.ADMIN.name()) // The role is ROLES_ADMIN
                .build();
        return new InMemoryUserDetailsManager(
                ozgurhanUser,
                aliUser
        );
    }
}
