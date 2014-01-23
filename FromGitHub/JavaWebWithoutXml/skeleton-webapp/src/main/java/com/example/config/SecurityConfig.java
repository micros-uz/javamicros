package com.example.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
   
   private static Logger log = LoggerFactory.getLogger(SecurityConfig.class);

   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
         .authorizeUrls()            
           //.antMatchers("/user/login").permitAll()
           //.antMatchers("/user/register").permitAll()
               .antMatchers("/secured/**").hasRole("ADMIN") 
               .antMatchers("/**").hasRole("USER") 
               .anyRequest().authenticated()
           .and()
               .formLogin()
                   .loginUrl("/user/login")
                   .loginPage("/user/login")
                   .defaultSuccessUrl("/user/me")
                   
                   //It seems that when specifying a custom login form, you also need to specify these for them to be populated.
                   //.usernameParameter("user")
                   //.passwordParameter("pwd")
                   .permitAll()
           
           ;
       log.info("Web security configured.");
   }
   
   @Override
   public void configure(WebSecurity builder) throws Exception {
       builder
           .ignoring()
               .antMatchers("/swagger-ui/**", "/css/**", "/images/**", "/js/**")
           ;
   }
}
