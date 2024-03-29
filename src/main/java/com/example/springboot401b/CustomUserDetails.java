package com.example.springboot401b;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CustomUserDetails extends org.springframework.security.core.userdetails.User {


    private User1 user;
    public CustomUserDetails(User1 user, Collection<? extends
            GrantedAuthority> authorities){
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }
    public CustomUserDetails(User1 user, String password, Collection<?
            extends GrantedAuthority> authorities){
    super(user.getUsername(), password, authorities);
    this.user = user;
    }


    public CustomUserDetails(User1 user, boolean enabled,
                             boolean accountNonExpired,
                             boolean credentialsNonExpired,
                             boolean accountNonLocked,
                             Collection<? extends GrantedAuthority> authorities)
    {
        super(user.getUsername(), user.getPassword(), enabled,
                accountNonExpired, credentialsNonExpired,
                accountNonLocked, authorities);
        this.user = user;
    }
    public User1 getUser() {
        return user;
    }
}
