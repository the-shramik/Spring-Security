package com.springsecurity.service;

import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl {

    @Autowired
    private IMyUserRepository userRepository;

    public MyUser saveUser(MyUser user){
        return userRepository.save(user);
    }
}
