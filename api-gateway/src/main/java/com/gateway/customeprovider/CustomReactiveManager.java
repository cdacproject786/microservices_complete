package com.gateway.customeprovider;

import com.gateway.jwt.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class CustomReactiveManager implements ReactiveAuthenticationManager {

    @Autowired
    private Jwtutil jwtutil;

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String token = (String)authentication.getPrincipal();
        boolean isValidated  = jwtutil.validateAccessToken(token);

        if(isValidated)
            return Mono.just(new UsernamePasswordAuthenticationToken(token,null,null));

        else
            //throw new Exception("Token validation failed");
            return null;
    }
}
