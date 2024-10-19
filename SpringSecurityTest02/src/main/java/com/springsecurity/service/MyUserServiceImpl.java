package com.springsecurity.service;

import com.springsecurity.exception.UserNamePresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserServiceImpl {

    @Autowired
    private IMyUserRepository userRepository;

    public MyUser registerUser(MyUser user) throws UserNamePresentException {

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return userRepository.save(user);
        } else {
            throw new UserNamePresentException("Email-id is already in user pliz try with another email..!");
        }
    }
}
