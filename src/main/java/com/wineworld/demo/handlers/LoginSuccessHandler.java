package com.wineworld.demo.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wineworld.demo.dtos.role.RoleLoginResponse;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private final UserRepository userRepository;

    public LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
            throws IOException, ServletException {
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        User user = userRepository.findByLogin(userDetails.getUsername()).orElseThrow(EntityExistsException::new);
        RoleLoginResponse roleLoginResponse = new RoleLoginResponse(user.getRole().getRoleId(), user.getRole().getName(),user.getUserId());
        String roleJson = new ObjectMapper().writeValueAsString(roleLoginResponse);
        response.getWriter().write(roleJson);
        response.setStatus(200);
        response.flushBuffer();
    }
}
