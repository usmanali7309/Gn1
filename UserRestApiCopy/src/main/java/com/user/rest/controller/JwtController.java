package com.user.rest.controller;

import com.user.rest.entities.AuthUser;
import com.user.rest.jwt.utils.JwtResponseModel;
import com.user.rest.jwt.utils.TokenManager;
import com.user.rest.services.impl.CustomAuthUserDetailseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/jwt")
public class JwtController {

    @Autowired
    private CustomAuthUserDetailseService customAuthUserDetailseService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenManager tokenManager;

    @PostMapping("/login")
    public ResponseEntity<?> createToken(@RequestBody AuthUser authUser) throws Exception {
        try {
            authenticationManager.authenticate(new
                    UsernamePasswordAuthenticationToken(authUser.getEmail(), authUser.getPassword()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }

         final UserDetails userDetails = customAuthUserDetailseService.loadUserByUsername(authUser.getEmail());
        final String jwtToken = tokenManager.generateJwtToken(userDetails);
        return ResponseEntity.ok(new JwtResponseModel(jwtToken));
    }
}