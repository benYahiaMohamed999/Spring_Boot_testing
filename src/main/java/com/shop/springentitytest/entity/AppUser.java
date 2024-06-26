package com.shop.springentitytest.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;


import java.util.List;

@Document(collection = "app-user")
@Data
@Builder

public class AppUser {
    @Id
    private String id;
    private String name ;
    private String username;
    private String password;
    private List<GrantedAuthority>authorities;


}
