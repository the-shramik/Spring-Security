package com.springsecurity.controller;

import com.springsecurity.constants.ApplicationConstants;
import com.springsecurity.exception.UserAlreadyPresentException;
import com.springsecurity.model.MyUser;
import com.springsecurity.model.dto.LoginRequestDto;
import com.springsecurity.model.dto.LoginResponseDto;
import com.springsecurity.repository.IMyUserRepository;
import com.springsecurity.service.MyUserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
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
@RequiredArgsConstructor
public class MyUserController {

    private final MyUserService userService;

    private final IMyUserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final Environment environment;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody MyUser user) throws UserAlreadyPresentException {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @RequestMapping("/user")
    public MyUser getUserDetailsAfterLogin(Authentication authentication) {
        Optional<MyUser> optionalCustomer = userRepository.findByEmail(authentication.getName());

        return optionalCustomer.orElse(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDto loginRequest) {

        System.out.println("login api is calling");
        try {

            String jwtToken = "";

            Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.username(), loginRequest.password());

            Authentication authenticatedResponse =
                    authenticationManager.authenticate(authentication);

            if (authenticatedResponse != null && authenticatedResponse.isAuthenticated()) {
                if (environment != null) {

                    String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                    SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                    jwtToken = Jwts.builder().issuer("SHRAMIK").subject("JWT-Token")
                            .claim("username", authenticatedResponse.getName())
                            .claim("authorities", authenticatedResponse.getAuthorities().stream().map(
                                            GrantedAuthority::getAuthority)
                                    .collect(Collectors.joining(","))).issuedAt(new Date())
                            .expiration(new Date((new Date()).getTime() + 30000000))
                            .signWith(secretKey).compact();
                }
            }

            System.out.println(authenticatedResponse.getName() + ":" + jwtToken);

            return ResponseEntity.status(HttpStatus.OK)
                    .header(ApplicationConstants.JWT_HEADER, jwtToken)
                    .body(new LoginResponseDto(authenticatedResponse.getName(),HttpStatus.OK.getReasonPhrase(), jwtToken));
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
