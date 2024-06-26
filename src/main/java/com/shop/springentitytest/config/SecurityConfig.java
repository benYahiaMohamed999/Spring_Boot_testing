package com.shop.springentitytest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class SecurityConfig {

    private final AuthenticationEntryPoint authenticationEntryPoint;

    private final  JWTAuthenticationFilter JWTAuthenticationFilter;

    public SecurityConfig(AuthenticationEntryPoint authenticationEntryPoint, com.shop.springentitytest.config.JWTAuthenticationFilter jwtAuthenticationFilter) {
        this.authenticationEntryPoint = authenticationEntryPoint;
        JWTAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public DefaultSecurityFilterChain securityConfig(HttpSecurity httpSecurity )throws Exception{

        //disable cors
        httpSecurity.cors(AbstractHttpConfigurer::disable);
        //disable csrf
        httpSecurity.csrf(AbstractHttpConfigurer::disable);
        //http request filter
        httpSecurity.authorizeHttpRequests(
                authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                        .requestMatchers("/api/auth/login/**")
                        .permitAll().requestMatchers("/api/auth/sing-up/**")
                        .permitAll().anyRequest().authenticated()
                        .requestMatchers("/error").permitAll());

        //Authentification Entry point =>Exeption handler

        httpSecurity.exceptionHandling(
                httpSecurityExceptionHandlingConfigurer -> httpSecurityExceptionHandlingConfigurer
                        .authenticationEntryPoint(
                        authenticationEntryPoint
                )
        );
        //Set session Policy=STETLESS
        httpSecurity.sessionManagement(
                httpSecuritySessionManagementConfigurer ->
                httpSecuritySessionManagementConfigurer
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //add jwt auth Filter

        httpSecurity.addFilterBefore(JWTAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();
    }
}
