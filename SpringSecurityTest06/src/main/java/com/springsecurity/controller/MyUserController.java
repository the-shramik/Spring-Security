package com.springsecurity.controller;

import com.springsecurity.constants.ApplicationConstants;
import com.springsecurity.model.MyUser;
import com.springsecurity.model.dto.LoginRequestDto;
import com.springsecurity.model.dto.LoginResponseDto;
import com.springsecurity.repository.IMyUserRepository;
import com.springsecurity.service.MyUserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class MyUserController {

    @Autowired
    private MyUserServiceImpl userService;

    @Autowired
    private IMyUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Environment environment;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> registerUSer(@RequestBody MyUser user){
       try {

           user.setPassword(passwordEncoder.encode(user.getPassword()));
           return ResponseEntity.ok(userService.addUser(user));
       }
       catch (Exception e){
           return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
       }
    }

    @RequestMapping("/user")
    public MyUser getUserDetailsAfterLogin(Authentication authentication) {
        Optional<MyUser> optionalCustomer = userRepository.findByEmail(authentication.getName());

        return optionalCustomer.orElse(null);
    }


    @PostMapping("/apiLogin")
    public ResponseEntity<?> apiLogin(@RequestBody LoginRequestDto loginRequest){
        String jwtToken="";

        Authentication authentication= UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username(),loginRequest.password());

        Authentication authenticatedResponse=authenticationManager.authenticate(authentication);

        if(authenticatedResponse!=null && authenticatedResponse.isAuthenticated()){

             if (environment!= null){

                 String secret=environment.getProperty(ApplicationConstants.JWT_SECRET_KEY,ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                 SecretKey secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                 jwtToken= Jwts.builder().issuer("SHRAMIK").subject("JWT-Token")
                         .claim("username",authenticatedResponse.getName())
                         .claim("authorities",authenticatedResponse.getAuthorities().stream().map(
                                 GrantedAuthority::getAuthority
                         ).collect(Collectors.joining(","))).issuedAt(new java.util.Date()).expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                         .signWith(secretKey).compact();
             }
        }

        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwtToken).body(new LoginResponseDto(HttpStatus.OK.getReasonPhrase(),jwtToken));
    }
}
