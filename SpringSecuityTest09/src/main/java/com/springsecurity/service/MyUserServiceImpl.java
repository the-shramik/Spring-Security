package com.springsecurity.service;

import com.springsecurity.exception.UserPresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserServiceImpl {

    private final IMyUserRepository userRepository;

    public MyUser registerUser(MyUser user) throws UserPresentException {

        if(userRepository.findByEmail(user.getEmail()).isEmpty()){
            return userRepository.save(user);
        }
        else{
            throw new UserPresentException("Account present with this email!");
        }
    }
}
