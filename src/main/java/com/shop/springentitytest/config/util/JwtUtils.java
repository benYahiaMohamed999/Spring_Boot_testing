package com.shop.springentitytest.config.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Slf4j
public class JwtUtils {
    private static final SecretKey secretKey=Jwts.SIG.HS256.key().build();


    private static final String ISSUER="coding_dtram_en server";

    private JwtUtils(){}
    public static boolean validateToken(String jwtToken) {

        return parseToken(jwtToken).isPresent();
    }

    private static Optional<Claims> parseToken(String jwtToken) {
        var jwtParser=Jwts.parser().verifyWith(secretKey).build();
        try {
            return Optional.of(jwtParser.parseSignedClaims(jwtToken).getPayload());
        }catch (JwtException e){
    log.error("JWT exeption ocuured");
        }
        return Optional.empty();
    }

    public static Optional<String> getUsernameFromToken(String jwtToken) {
       var claimsOptional= parseToken(jwtToken);

       if (claimsOptional.isPresent()){
           return Optional.of(claimsOptional.get().getSubject());
       }
       return Optional.empty();
    }


    public static String genrateToken(String username) {

        var JWTEXparitionENMinut=10;

        var currentdate=new Date();
        var exparition=DateUtils.addMinutes(currentdate,JWTEXparitionENMinut);

        return Jwts.builder().id(UUID.randomUUID().toString())
                .issuer(ISSUER)
                .subject(username)
                .signWith(secretKey)
                .issuedAt(currentdate)
                .expiration(exparition).compact();

    }
}
