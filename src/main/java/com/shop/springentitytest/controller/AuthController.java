package com.shop.springentitytest.controller;

import com.shop.springentitytest.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto>login(@RequestBody AuthRequestDto authRequestDto){
       var jwtToken= authService.login(authRequestDto.username(),authRequestDto.password());

      var authResponseDTO =new AuthResponseDto(jwtToken,AuthStatus.LOGIN_SUCCESS);
       return  ResponseEntity.status(HttpStatus.OK).body(authResponseDTO);
    }


    @PostMapping("/sign-up")
    public ResponseEntity<AuthResponseDto>signUp(@RequestBody AuthRequestDto authRequestDto){
       try {
           var jwtToken= authService.signUp(authRequestDto.name(),authRequestDto.username(),authRequestDto.password());

           var authResponseDTO =new AuthResponseDto(jwtToken,AuthStatus.USER_SUCCESS_CREATE);
           return  ResponseEntity.status(HttpStatus.OK).body(authResponseDTO);
       }catch (Exception e){

           var authResponseDTO =new AuthResponseDto(null,AuthStatus.USER_NOT_CREATE);

           return  ResponseEntity.status(HttpStatus.CONFLICT).body(authResponseDTO);
       }
    }
}
