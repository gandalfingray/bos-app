package com.mintit.bos.auth.controller;

import javax.servlet.http.HttpSession;

import com.mintit.bos.auth.model.AuthenticationRequest;
import com.mintit.bos.auth.model.AuthenticationToken;
import com.mintit.bos.auth.model.User;
import com.mintit.bos.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.web.bind.annotation.*;


@RestController
public class LoginController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @RequestMapping(value="/login", method=RequestMethod.POST)
    public AuthenticationToken login(
            @RequestBody AuthenticationRequest authenticationRequest,
            HttpSession session) {
        System.out.println("Login processing " + authenticationRequest.getUsername());
        String username = authenticationRequest.getUsername();
        String password = authenticationRequest.getPassword();

        try {

            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password);

            Authentication authentication = authenticationManager.authenticate(token);

            SecurityContextHolder.getContext().setAuthentication(authentication);

            session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY,
                    SecurityContextHolder.getContext());

            User user = userService.readUser(username);

            return new AuthenticationToken(user.getUsername(), user.getAuthorities(), session.getId());
        }
        catch(AuthenticationException ae){
            logger.error(String.format("Userid %s login failed!", username));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/user/{userId}")
    public User getUser(@PathVariable String userId, HttpSession session){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        System.out.println("Login User "+user.getUsername());
        return userService.readUser(userId);
    }
}