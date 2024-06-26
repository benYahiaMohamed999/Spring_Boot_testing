package com.shop.springentitytest.service.auth;

import com.shop.springentitytest.config.util.JwtUtils;
import com.shop.springentitytest.entity.AppUser;
import com.shop.springentitytest.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final AppUserRepository appUserRepository;

    public void save(AppUser appUser) {
        appUserRepository.save(appUser);
    }
    @Override
    public String login(String username, String password) {
       var authToken=new UsernamePasswordAuthenticationToken(username,password);

       var authenticate=authenticationManager.authenticate(authToken);




        //Generate  token

        return JwtUtils.genrateToken( ( (UserDetails)(authenticate.getPrincipal())).getUsername());
    }


    @Override
    public String signUp(String name, String username, String password) {

        //check user already existe
        if(appUserRepository.existsByUsername(username)){
            throw new RuntimeException("user already existe");
        }

        //ENcoded password
        var encodedPassword=passwordEncoder.encode(password);

        //Authorities
        var authorities=new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        //Create app user
        AppUser.builder()
                .name(name)
                .username(username)
                .password(encodedPassword)
                .authorities(authorities)
                .build();



        //save USer
       // AppUserRepository.save(AppUser);

    //Generate  token

        return JwtUtils.genrateToken(username);

    }


}
