package com.example.springboot401b;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    public User findByemail(String email)
    {
        return userRepository.findByEmail(email);
    }
    public Long countByEmail(String email)
    {
        return userRepository.countByEmail(email);
    }
    public User findByUsername(String username)
    {
        return userRepository.findByUsername(username);
    }
    public void saveUser(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("USER")));
        user.setEnabled(true);
        userRepository.save(user);
    }
    public void saveAdmin(User user){
        user.setRoles(Arrays.asList(roleRepository.findByRole("ADMIN")));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
