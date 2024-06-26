package com.shop.springentitytest.config;

import com.shop.springentitytest.config.util.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;
@RequiredArgsConstructor
@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {


    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        //Fetch Token from request
        var jwtTokenOptionel=getTokenFromRequest(request);

        //validate token=>JWT utils
        jwtTokenOptionel.ifPresent(jwtToken -> {
            if (JwtUtils.validateToken(jwtToken)) {
                // Get Username from jwt token
                var usernameOptionel = JwtUtils.getUsernameFromToken(jwtToken);

                usernameOptionel.ifPresent(username ->{

                    //Fetch user details with username
                    var userDetails=userDetailsService.loadUserByUsername(username);
                    //Create Authentication token
                    var authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,

                            userDetails.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    //set AuthToken to secutity context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                });



            }
        });;//na9sa 20:19



        //pass rquest and response to next filter
        filterChain.doFilter(request,response);


    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request){
        //Extract authentication header
       var authHeader= request.getHeader(HttpHeaders.AUTHORIZATION );

       //bearer <JWT TOKEN>

        if(StringUtils.hasText(authHeader)&& authHeader.startsWith("Bearer ")){
            return Optional.of(authHeader.substring(7));

        }
        return  Optional.empty();


    }
}
