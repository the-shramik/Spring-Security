package com.example.springsecuritytest05.service;

import com.example.springsecuritytest05.model.MyUser;
import com.example.springsecuritytest05.repository.IMyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private IMyUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        MyUser user =userRepository.findByEmail(email).orElseThrow(
                ()->new UsernameNotFoundException("User is not present with this email"));

        List<GrantedAuthority> authorities=List.of(new SimpleGrantedAuthority("ROLE_"+user.getRole()));
        return new User(user.getEmail(), user.getPassword(),authorities);
    }
}
