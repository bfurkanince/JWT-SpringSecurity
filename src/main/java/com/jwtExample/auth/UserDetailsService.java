package com.jwtExample.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private Map<String,String>  users = new HashMap<>();

    @PostConstruct
    public void initFakeUser(){
        users.put("furkan" , bCryptPasswordEncoder.encode("123456"));
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        if(users.containsKey(userName)){
            return new User(userName , users.get(userName),new ArrayList<>());
        }
        throw new UsernameNotFoundException(userName);
    }
}
