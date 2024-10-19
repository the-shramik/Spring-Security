package com.springsecurity.config;

import com.springsecurity.model.Authority;
import com.springsecurity.model.Customer;
import com.springsecurity.repository.ICustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ICustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("User details not found for this email " + username));

        List<GrantedAuthority> authorities= customer.getAuthorities().stream().map(authority ->
            new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());

        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
