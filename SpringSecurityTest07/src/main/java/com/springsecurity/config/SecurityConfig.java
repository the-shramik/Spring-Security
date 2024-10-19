package com.springsecurity.config;

import com.springsecurity.filter.JwtTokenValidatorFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Avoiding to creating JSessionId and storing into Cookies
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
                .addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .authorizeHttpRequests(request->request
                        .requestMatchers("/error","/register","/login").permitAll()
                        .requestMatchers("/registerPatient","/viewPatients","/deletePatient/*").hasRole("ADMIN")
                        .requestMatchers("/hospitalServices","/contact").hasRole("USER").anyRequest().authenticated());

//                http.formLogin(Customizer.withDefaults());
                http.httpBasic(Customizer.withDefaults());
                return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    AuthenticationManager  authenticationManager(UserDetailsService userDetailsService,
                                                 PasswordEncoder passwordEncoder){

        CustomAuthenticationProvider authenticationProvider=
                new CustomAuthenticationProvider(userDetailsService,passwordEncoder);

        ProviderManager providerManager=new ProviderManager(authenticationProvider);

        providerManager.setEraseCredentialsAfterAuthentication(false);

        return providerManager;
    }
}
