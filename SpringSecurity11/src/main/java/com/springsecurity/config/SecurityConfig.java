package com.springsecurity.config;

import com.springsecurity.exceptionhandling.CustomAccessDeniedHandler;
import com.springsecurity.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import com.springsecurity.filter.CsrfCookieFilter;
import com.springsecurity.filter.JwtTokenGeneratorFilter;
import com.springsecurity.filter.JwtTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;


import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain mySecurityFilterChain(HttpSecurity http) throws Exception {

        CsrfTokenRequestAttributeHandler csrfTokenRequestAttributeHandler = new CsrfTokenRequestAttributeHandler();

        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(corsCfg -> corsCfg.configurationSource(new CorsConfigurationSource() {
                    @Override
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowCredentials(true);
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(Arrays.asList("Authorization"));
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .csrf(csrfConfig -> csrfConfig.csrfTokenRequestHandler((csrfTokenRequestAttributeHandler)) //Configured to read an token from request
                        .ignoringRequestMatchers("/contact", "/register","/apiLogin") //Configuring to hit these mentioned API's not required an CSRF-Token
                        .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())) //Declaring an storing a CSRF token inside a cookie and withHttpOnlyFalse() is used to allow an UI applications to read csrf token automatically
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class) //Configuring filter name which generating token and when it should generates
                .addFilterAfter(new JwtTokenGeneratorFilter(),BasicAuthenticationFilter.class)
                .addFilterBefore(new JwtTokenValidatorFilter(),BasicAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> requests
                                .requestMatchers("/myAccount").hasRole("USER")
                                .requestMatchers("/myBalance").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/myLoans").hasRole("USER")
                                .requestMatchers("/myCards").hasRole("USER")
                                .requestMatchers("/user").authenticated()
                                .requestMatchers("/notices", "/contact", "/error", "/register", "/invalidSession", "/getAllCustomers","/apiLogin").permitAll()
                );
        http.formLogin(withDefaults());
        http.httpBasic(hbc -> hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));

        http.exceptionHandling(ehc -> ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService,PasswordEncoder passwordEncoder){

         MyUserNamePwdAuthenticationProvider authenticationProvider=
                 new MyUserNamePwdAuthenticationProvider(userDetailsService,passwordEncoder);

        ProviderManager providerManager=new ProviderManager(authenticationProvider);
        providerManager.setEraseCredentialsAfterAuthentication(false);
        return providerManager;
    }

}
