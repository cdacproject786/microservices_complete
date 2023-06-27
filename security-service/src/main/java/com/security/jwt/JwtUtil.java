package com.security.jwt;

import com.security.pojo.Customerpojo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_DURATION = 20 * 60 * 60 * 1000; //24 HOURS

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

    private String SECRET_KEY = "SECRET_KEY";

    public String generateAccessToken(Customerpojo user)
    {
        return Jwts.builder()
                .setSubject(String.format("%s",user.getEmail()))
                .setIssuer("Auth-Service")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DURATION))
                .signWith(SignatureAlgorithm.HS512,SECRET_KEY)
                .compact();
    }

}
