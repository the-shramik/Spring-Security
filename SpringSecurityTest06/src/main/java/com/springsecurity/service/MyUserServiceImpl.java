package com.springsecurity.service;

import com.springsecurity.Exception.UserNameAlreadyPresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl {

    @Autowired
    private IMyUserRepository userRepository;

    public MyUser addUser(MyUser user) throws UserNameAlreadyPresentException {
        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }else {
            throw new UserNameAlreadyPresentException("User had already present with this email");
        }
    }
}
