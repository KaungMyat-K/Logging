package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.test.entity.Token;
import com.test.entity.User;
import com.test.enums.TokenType;
import com.test.repo.TokenRepo;
import com.test.repo.UserRepo;
import com.test.request.AuthenticationRequest;
import com.test.response.AuthenticationResponse;
import com.test.security.JwtService;


@Service
public class AuthenticationService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenRepo tokenRepo;
    
    public AuthenticationResponse authenticate(AuthenticationRequest data){
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword())   
        );
        var user = userRepo.findByEmail(data.getEmail())
                            .orElseThrow(
                                ()->new UsernameNotFoundException("Login fail! Emai :  "+data.getEmail()+" not found")
                            );
        var jwtToken = jwtService.generateToken(user);
        revokedAllUserToken(user);
        var token = Token.builder()
                    .user(user)
                    .token(jwtToken)
                    .tokenType(TokenType.BEARER)
                    .revoked(false)
                    .expired(false)
                    .build();
        tokenRepo.save(token);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    private void revokedAllUserToken(User user){
        var validUserToken = tokenRepo.findAllValidTokenByUser(user.getId());
        if (validUserToken.isEmpty()) {
            return;
        }
        validUserToken.forEach(token -> {
            token.setRevoked(true);
            token.setExpired(true);
        });
        tokenRepo.saveAll(validUserToken);
    }
}
