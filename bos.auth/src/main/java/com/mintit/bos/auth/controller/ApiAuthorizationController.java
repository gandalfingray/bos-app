package com.mintit.bos.auth.controller;

import com.mintit.bos.auth.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ApiAuthorizationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/api/**")
    public void authorizeRequest(){
        System.out.println("Authorization processing...");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Collection authorities = user.getAuthorities();
        authorities.forEach((authority)-> {
            System.out.println( user.getUsername() + " has authority " + authority);
        });
    }
}
