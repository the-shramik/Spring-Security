package com.springsecurity.service;

import com.springsecurity.exception.UserAlreadyPresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.repository.IMyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final IMyUserRepository userRepository;

    public MyUser registerUser(MyUser user) throws UserAlreadyPresentException {

        if(userRepository.findByEmail(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }
        else {
            throw new UserAlreadyPresentException("The has already registered with this email");
        }
    }
}
