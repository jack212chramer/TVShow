package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.service.UserDetailsServiceImp;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
  @Bean
  public UserDetailsService userDetailsService() {
    return new UserDetailsServiceImp();
  };
  
  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  };
  
  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    .authorizeRequests()
    	.antMatchers("/","/homepage","/header","/footer","/joinUs","/sayHello","/register","/registerCheck",
    			"/newsList","/news","/userAlreadyExists","/comments","/tvShows","/serial","/nextAirDate",
    			"/top25","/seasons","/myShows","/search","/resources/**").permitAll()
    	.antMatchers("/adminPanel","/userPanel","/rolPanel","/getAddRole","/addRole","/newShow","/editShow",
    			"/addShow","/updateShow","/deleteRole","/personPanel","/addPerson","/updatePerson","/deletePerson",
    			"/showPanel","/deleteShow","/banUser","/increaseAdminPrivileges","/decreaseAdminPrivileges",
    			"/createNews","/addnews","/newsEdit","/updateNews","/newsDelete").hasRole("ADMIN")
    	.anyRequest().authenticated()
	    .and()
    .formLogin()
    	.loginPage("/login")
    	.loginProcessingUrl("/signin") 
    	.usernameParameter("username")
    	.passwordParameter("password")
    	.defaultSuccessUrl("/loginCheck")
    	.failureUrl("/loginCheck")
    	.permitAll()
    	.and()
    .logout().permitAll().logoutSuccessUrl("/")
    	.and()
    .csrf().disable();
  }
  
}