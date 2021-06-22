package com.mintit.bos.auth.service;

import com.mintit.bos.auth.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder;

    private User user;

    public UserService(){
        user = new User();
        user.setUsername("hello");
        user.setAUTHORITY("USER");
        passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode("world"));
        user.setENABLED(true);
    }

    public User readUser(String userName){
//        User user = new User();
//        user.setUsername("hello");
//        System.out.println("User service " + userName);
//        System.out.println("User service this.userName "  + user.getUsername());
//        System.out.println("compare username and user.getUsername " + userName.equals(user.getUsername()));
//        System.out.println("authorities "+ user.getAuthorities());
//        System.out.println("passowrd " + user.getPassword());
        return userName.equals(user.getUsername()) ? user : null ;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        CustomUserDetails user = userAuthDAO.getUserById(username);
//        if(user==null) {
//            throw new UsernameNotFoundException(username);
//        }
        UserDetails user = this.readUser(username);
        System.out.println("loadUserByUsername called "+ user);
        return user;
    }

    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }
}