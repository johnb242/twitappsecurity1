package com.example.springboot401b;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.HashSet;
import java.util.Set;



    @Transactional
    @Service
    public class SSUserDetailsService implements UserDetailsService {

        private UserRepository userRepository;

        public SSUserDetailsService(UserRepository userRepository) {
            this.userRepository = userRepository;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws
                UsernameNotFoundException {
            try {
                User appUser = userRepository.findByUsername(username);

                if (appUser == null) {
                    System.out.println("user not found with the provided surname" + appUser.toString());
                    return null;
                }
                System.out.println("user from username " + appUser.toString());
//                return new org.springframework.security.core.userdetails.User(
//                        appUser.getUsername(),
//                        appUser.getPassword(),
//                        getAuthorities(appUser));
                return new CustomUserDetails(appUser, getAuthorities(appUser));
            } catch (Exception e) {
                throw new UsernameNotFoundException("user not found");
            }
        }

        private Set<GrantedAuthority> getAuthorities(User appUser) {
            Set<GrantedAuthority> authorities = new HashSet<>();

            for (Role role : appUser.getRoles()) {
                GrantedAuthority grantedAuthority =
                        new SimpleGrantedAuthority(role.getRole());
                authorities.add(grantedAuthority);
            }
            System.out.println("user authorities are" + authorities.toString());
            return authorities;
        }
    }



