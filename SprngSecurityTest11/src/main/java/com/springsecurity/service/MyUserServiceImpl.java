package com.springsecurity.service;

import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserServiceImpl {

    private final IMyUserRepository userRepository;

    public MyUser registerUser(MyUser user){
        return userRepository.save(user);
    }
}
