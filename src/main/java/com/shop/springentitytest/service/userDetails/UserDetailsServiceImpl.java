package com.shop.springentitytest.service.userDetails;

import com.shop.springentitytest.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {


    private final AppUserRepository appUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var appUser=appUserRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("USERNAME NOT FOUN"));

        return new User(appUser.toString(), appUser.toString(),null);
    }
}
