package com.wineworld.demo.config;

import com.wineworld.demo.handlers.LoginFailureHandler;
import com.wineworld.demo.handlers.LoginSuccessHandler;
import com.wineworld.demo.handlers.LogoutHandler;
import com.wineworld.demo.repositories.UserRepository;
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
   private final UserRepository userRepository;

   public SecurityConfig(UserDetailsService userDetailsService, UserRepository userRepository) {
         this.userRepository = userRepository;
         this.loginSuccessHandler = new LoginSuccessHandler(userRepository);
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
               .deleteCookies("JSESSIONID")
               .logoutSuccessHandler(logoutHandler)
               .and()
               .exceptionHandling()
               .and()
               .authorizeRequests()
                   .regexMatchers("^.*\\/get.*$")
                   .permitAll()
                   .antMatchers("/v2/api-docs",
                           "/configuration/ui",
                           "/swagger-resources/**",
                           "/configuration/security",
                           "/swagger-ui.html",
                           "/webjars/**")
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
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
                .allowCredentials(true)
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials");
            }
        };
    }

    // @Bean
    // public WebMvcConfigurationSupport webConfigure(){
    //     return new WebMvcConfigurationSupport(){
    //         @Override
    //         public void addResourceHandlers(ResourceHandlerRegistry registry) { 
    //                 registry.addResourceHandler("/**")
    //                      .addResourceLocations("classpath:/static/");
    //         }
    //     };
    // }
}
