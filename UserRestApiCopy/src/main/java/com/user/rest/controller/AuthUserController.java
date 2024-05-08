package com.user.rest.controller;

import com.user.rest.dto.AuthUserDto;
import com.user.rest.entities.AuthUser;
import com.user.rest.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("authuser/api")
public class AuthUserController {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody AuthUserDto authUserDto){

        if(authUserRepository.existsByUsername(authUserDto.getUsername())){
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // add check for email exists in DB
        if(authUserRepository.existsByEmail(authUserDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        AuthUser authUser = new AuthUser();

        authUser.setUsername(authUserDto.getUsername());
        authUser.setEmail(authUserDto.getEmail());
        authUser.setPassword(passwordEncoder.encode(authUserDto.getPassword()));

        AuthUser saved = authUserRepository.save(authUser);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);

    }


}
