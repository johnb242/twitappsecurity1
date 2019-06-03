package com.example.springboot401b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    User1Repository userRepository;

    @Autowired
    public UserService(User1Repository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User1 findByemail(String email)
    {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email)
    {
        return userRepository.countByEmail(email);
    }
    public User1 findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    public void saveUser(User1 user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void saveAdmin(User1 user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public User1 getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentusername=authentication.getName();
        User1 user = userRepository.findByUsername(currentusername);
        return user;
    }
}
