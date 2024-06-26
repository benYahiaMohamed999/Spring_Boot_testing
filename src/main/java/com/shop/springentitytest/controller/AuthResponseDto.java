package com.shop.springentitytest.controller;


public record AuthResponseDto(String token, AuthStatus authStatus) {
}
