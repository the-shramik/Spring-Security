package com.springsecurity.controller;

import com.springsecurity.constants.ApplicationConstant;
import com.springsecurity.exception.UserPresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.model.dto.LoginRequestDto;
import com.springsecurity.model.dto.LoginResponseDto;
import com.springsecurity.service.MyUserServiceImpl;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
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
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserServiceImpl userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final Environment environment;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) throws UserPresentException {
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<?> apiLogin(@RequestBody LoginRequestDto loginRequest){
         String jwtToken="";

        Authentication authentication= UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email(),loginRequest.password());

        Authentication authenticationResponse=authenticationManager.authenticate(authentication);

        if(authenticationResponse!=null && authenticationResponse.isAuthenticated()){

            if(environment!=null){
                String secret=environment.getProperty(ApplicationConstant.JWT_SECRET_KEY,ApplicationConstant.JWT_SECRET_DEFAULT_VALUE);

                SecretKey secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                jwtToken= Jwts.builder().issuer("SHRAMIK").subject("JWT-Token")
                        .claim("username",authenticationResponse.getName())
                        .claim("authorities",authenticationResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority
                        ).collect(Collectors.joining(","))).issuedAt(new java.util.Date()).expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstant.JWT_HEADER,jwtToken).body(new LoginResponseDto(authenticationResponse.getName(),HttpStatus.OK.getReasonPhrase(),jwtToken));
    }
}
