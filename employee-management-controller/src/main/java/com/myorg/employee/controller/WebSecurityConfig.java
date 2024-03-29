package com.myorg.employee.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SimpleUrlAuthenticationFailureHandler failureHandler = new SimpleUrlAuthenticationFailureHandler();
        //failureHandler.setUseForward(true);
        http
                .cors().disable()
                .csrf().disable()
                .addFilterBefore(new CaptchaFilter(failureHandler), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/user/**", "/employee/**", "/department/**", "/employee-department/**").authenticated()
                .antMatchers("/login-check").authenticated()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .failureHandler(failureHandler)
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }
}