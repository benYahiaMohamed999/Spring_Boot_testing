package com.shop.springentitytest.service.auth;

public interface AuthService {
    String login(String username, String password);

    String signUp(String name, String username, String password);


}
