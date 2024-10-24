package com.springsecurity.controller;

import com.springsecurity.constant.ApplicationConstants;
import com.springsecurity.model.Customer;
import com.springsecurity.model.dto.LoginRequestDTO;
import com.springsecurity.model.dto.LoginResponseDTO;
import com.springsecurity.repository.ICustomerRepository;
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
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class CustomerController {

    @Autowired
    private ICustomerRepository customerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private Environment environment;

    @PostMapping("/register")
    public ResponseEntity<?> registerCustomer(@RequestBody Customer customer) {
        try {
            String hashPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashPwd);
            customer.setCreateDt(new Date(System.currentTimeMillis()));
            Customer savedCustomer = customerRepository.save(customer);

            if (savedCustomer.getId() > 0) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Registration successfully done..!");
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Registration failed..!");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @RequestMapping("/user")
    public Customer getUserDetailsAfterLogin(Authentication authentication) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmail(authentication.getName());

        return optionalCustomer.orElse(null);
    }

    @GetMapping("/getAllCustomers")
    public ResponseEntity<?> getAllCustomers(){

        customerRepository.findAll().forEach(customer -> {
            customer.getAuthorities().forEach(authority -> {
                System.out.println(authority.getName());
            });
            System.out.println(customer.getAuthorities());
        });
        return ResponseEntity.ok(customerRepository.findAll());
    }

    @GetMapping("/getCustomerWithAuthorities/{id}")
    public ResponseEntity<?> getCustomerWithAuth(@PathVariable Long id){
        return ResponseEntity.ok(customerRepository.findCustomerWithAuthorities(id));
    }

    @PostMapping("/apiLogin")
    public ResponseEntity<?> apiLogin(@RequestBody LoginRequestDTO loginRequest){

        String jwtToken="";

        Authentication authentication= UsernamePasswordAuthenticationToken
                .unauthenticated(loginRequest.username(),loginRequest.password());

        Authentication authenticateResponse=authenticationManager.authenticate(authentication);

        if(authenticateResponse!=null && authenticateResponse.isAuthenticated()){

            if (environment != null) {

                String secret = environment.getProperty(ApplicationConstants.JWT_SECRET_KEY, ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                jwtToken = Jwts.builder().issuer("My Bank").subject("JWT Token")
                        .claim("username", authenticateResponse.getName())
                        .claim("authorities", authenticateResponse.getAuthorities().stream().map(
                                GrantedAuthority::getAuthority
                        ).collect(Collectors.joining(","))).issuedAt(new java.util.Date()).expiration(new java.util.Date((new java.util.Date()).getTime() + 30000000))
                        .signWith(secretKey).compact();
            }
        }

        return ResponseEntity.status(HttpStatus.OK).header(ApplicationConstants.JWT_HEADER,jwtToken)
                .body(new LoginResponseDTO(HttpStatus.OK.getReasonPhrase(),jwtToken));
    }
}
