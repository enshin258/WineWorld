package com.wineworld.demo.config;

import com.wineworld.demo.handlers.LoginFailureHandler;
import com.wineworld.demo.handlers.LoginSuccessHandler;
import com.wineworld.demo.handlers.LogoutHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

   private final LoginSuccessHandler loginSuccessHandler;
   private final LoginFailureHandler loginFailureHandler;
   private final LogoutHandler logoutHandler;
   private final UserDetailsService userDetailsService;

   public SecurityConfig(UserDetailsService userDetailsService) {
         this.loginSuccessHandler = new LoginSuccessHandler();
         this.loginFailureHandler = new LoginFailureHandler();
         this.logoutHandler = new LogoutHandler();
         this.userDetailsService = userDetailsService;
   }

   @Override
   public void configure(HttpSecurity httpSecurity) throws Exception {
       httpSecurity.cors().and()
               .csrf().disable()               
               .formLogin()
               .successHandler(loginSuccessHandler) 
               .failureHandler(loginFailureHandler)
               .and() 
               .logout() 
               .logoutSuccessHandler(logoutHandler)
               .and()
               .exceptionHandling()
               .and()
               .authorizeRequests()
                   .antMatchers("/**")
                   .permitAll()
                   .and()
               .authorizeRequests()
                   .antMatchers("/payment").hasAnyAuthority("USER", "ADMIN")
                   .regexMatchers("^.*\\/update.*$").hasAuthority("ADMIN")
                   .regexMatchers("^.*\\/delete.*$").hasAuthority("ADMIN")
                   .regexMatchers("^.*\\/add.*$").hasAuthority("ADMIN")
                   .anyRequest()
                   .authenticated();

   }

   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   }

   @Bean
   private static PasswordEncoder passwordEncoder() { 
      return new BCryptPasswordEncoder();
   }

   @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");
            }
        };
    }
}
