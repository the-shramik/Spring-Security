package com.springsecurity.service;

import com.springsecurity.model.Employee;
import com.springsecurity.repository.IEmployeeRepository;
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
    private IEmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        System.out.println("my user details service working...!");
        System.out.println(email);
        Employee employee = employeeRepository.findByEmail(email).orElseThrow(

                () -> new UsernameNotFoundException("The employee is not present with email" + email));

        System.out.println(employee);
        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(employee.getRole()));

        return new User(employee.getEmail(), employee.getPassword(), authorities);
    }
}
