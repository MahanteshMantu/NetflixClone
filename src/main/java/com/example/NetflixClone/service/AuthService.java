package com.example.NetflixClone.service;

import com.example.NetflixClone.accessor.AuthAccessor;
import com.example.NetflixClone.accessor.UserAccessor;
import com.example.NetflixClone.accessor.model.UserDTO;
import com.example.NetflixClone.exceptions.DependencyFailureException;
import com.example.NetflixClone.exceptions.InvalidCredentialsException;
import com.example.NetflixClone.security.SecurityConstants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class AuthService {
    @Autowired
    private UserAccessor userAccessor;
    @Autowired
    private AuthAccessor authAccessor;

  /*  @param email: email of user who wants to log-in
       @param password: password of user who wants to log-in
       @return jwt token if credentials match*/

    public String login(String email, String password){
        UserDTO userDTO = UserAccessor.getUserByEmail(email);

            if (userDTO != null && userDTO.getPassword().equals(password)) {
                String token = Jwts.builder()
                        .setSubject(email)
                        .setAudience(userDTO.getRole().name())
                        .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                        .signWith(SignatureAlgorithm.HS512, SecurityConstants.SECRET_KEY)
                        .compact();
                authAccessor.storeToken(userDTO.getUserId(), token);
                return token;
            }
            throw new InvalidCredentialsException("UserID/Password Incorrect!");
        }
}
