package com.user.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.Filter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
//        extends WebSecurityConfigurerAdapter {
{
    private static final String[] swagger = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v2/api-docs",
            "/webjars/**"
    };

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter filter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

      http.csrf().disable()
                .authorizeRequests(requests -> requests
                 .antMatchers(HttpMethod.GET, "/user/api/**").permitAll()
                        .antMatchers("/api/jwt/**").permitAll()
                        .antMatchers("/authuser/api/**").permitAll()
                        .antMatchers(swagger).permitAll()
                        .anyRequest().authenticated());

        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeRequests(requests -> requests
//                        .antMatchers(HttpMethod.GET, "/user/api/**").permitAll()
//                        .antMatchers("/api/jwt/**").permitAll()
//                        .antMatchers("/authuser/api/**").permitAll()
//                        .antMatchers(swagger).permitAll()
//                        .anyRequest().authenticated());
//
//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
//          }

//        @Override
//        protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
//        }

    @Bean
    public AuthenticationManager authenticationManagerBean(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

        @Bean
        public AuthenticationManager authenticationManager (
                AuthenticationConfiguration configuration) throws Exception {
            return configuration.getAuthenticationManager();
        }
        @Bean
        public PasswordEncoder passwordEncoder () {
            return new BCryptPasswordEncoder(8);
        }
}