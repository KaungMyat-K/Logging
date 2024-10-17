package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.entity.Token;
import com.test.entity.User;
import com.test.enums.Role;
import com.test.enums.TokenType;
import com.test.repo.TokenRepo;
import com.test.repo.UserRepo;
import com.test.request.RegisterRequest;
import com.test.response.AuthenticationResponse;
import com.test.security.JwtService;


@Service
public class UserService {
    
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private TokenRepo tokenRepo;

    public AuthenticationResponse saveUser(RegisterRequest data){
        User user = User.builder()
                    .name(data.getName())
                    .email(data.getEmail())
                    .password(encoder.encode(data.getPassword()))
                    .role(Role.USER)
                    .build();
        var savedUser = userRepo.save(user);
        var jwtToken = jwtService.generateToken(user);
        var token = Token.builder()
                    .user(savedUser)
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

}
