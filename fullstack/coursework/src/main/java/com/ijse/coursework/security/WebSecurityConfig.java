package com.ijse.coursework.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ijse.coursework.security.jwt.AuthEntryPoint;
import com.ijse.coursework.security.jwt.AuthTokeanFilter;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {
    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public UserDetailsService userDetailsService(){
        return userDetailsServiceImpl;
    }

    @Bean
    public AuthTokeanFilter authenticationTokeanFilter(){
        return new AuthTokeanFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration athConfig) throws Exception {
        return athConfig.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http)throws Exception{
        http
            .csrf(csrf->csrf.disable())
            .exceptionHandling(exception->exception.authenticationEntryPoint(authEntryPoint))
            .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth->auth.requestMatchers("/auth/***").permitAll()
            // .authorizeHttpRequests(auth->auth.requestMatchers("/invoice/generate").permitAll()
            .anyRequest().authenticated()
            );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationTokeanFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
