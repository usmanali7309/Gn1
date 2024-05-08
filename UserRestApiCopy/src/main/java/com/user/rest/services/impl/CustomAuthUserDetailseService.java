package com.user.rest.services.impl;

import com.user.rest.entities.AuthUser;
import com.user.rest.repositories.AuthUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomAuthUserDetailseService implements UserDetailsService {
    @Autowired
    private AuthUserRepository authUserRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            AuthUser user = authUserRepository.findByUsernameOrEmail(username, username).orElseThrow(
                    ()-> new UsernameNotFoundException("User not fount with username or Email"+username));
//        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ADMIN");

            return new User(user.getEmail(),user.getPassword(),new ArrayList<>());
        }
}
