package com.springsecurity.filter;

import com.springsecurity.constants.ApplicationConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String jwtToken=request.getHeader(ApplicationConstants.JWT_HEADER);

        if(jwtToken!=null){
            try {
                Environment environment=getEnvironment();

                if(environment!=null){
                    String secret=environment.getProperty(ApplicationConstants.JWT_SECRET_KEY,ApplicationConstants.JWT_SECRET_DEFAULT_VALUE);

                    SecretKey secretKey= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

                    if(secretKey!=null){
                        Claims claims= Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken).getPayload();

                        String userName=String.valueOf(claims.get("username"));
                        String authorities=String.valueOf(claims.get("authorities"));

                        Authentication authentication=
                                new UsernamePasswordAuthenticationToken(userName,null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));


                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }catch (Exception e){
                 throw new BadCredentialsException("Invalid JWT token received..!");
            }
        }
        filterChain.doFilter(request,response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return request.getServletPath().equals("/user");
    }
}
