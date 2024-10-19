package com.springsecurity.service;

import com.springsecurity.model.MyUser;
import com.springsecurity.reposiroty.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private IUserRepository userRepository;

    public MyUser saveUser(MyUser user){
        return userRepository.save(user);
    }
}
