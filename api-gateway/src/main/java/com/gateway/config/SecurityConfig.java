package com.gateway.config;

import com.gateway.jwt.Jwtutil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFlux
public class SecurityConfig {

    @Autowired
    private ServerSecurityContextRepository customeSecurityContext;
    @Autowired
    private ReactiveAuthenticationManager authenticationManager;
    @Bean
    public Jwtutil jwtutil()
    {
        return new Jwtutil();
    }
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }


    @Bean
    protected SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity.csrf().disable();
        serverHttpSecurity.formLogin().disable();

        serverHttpSecurity.authorizeExchange()
                .pathMatchers(HttpMethod.POST, "/security/saveuser").permitAll()
                .pathMatchers(HttpMethod.POST,"/security/login").permitAll()
                //.pathMatchers(HttpMethod.GET,"/user/security/demo").permitAll()
                .pathMatchers(HttpMethod.POST,"/api/login/user").permitAll()
                .anyExchange().authenticated();

        serverHttpSecurity.securityContextRepository(this.customeSecurityContext);
        serverHttpSecurity.authenticationManager(this.authenticationManager);
        serverHttpSecurity.exceptionHandling()
                .authenticationEntryPoint((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED))
                ).accessDeniedHandler((swe, e) ->
                        Mono.fromRunnable(() -> swe.getResponse().setStatusCode(HttpStatus.FORBIDDEN))
                );

        return serverHttpSecurity.build();
    }
    //@Bean
    //public AuthenticationProvider authenticationProvider()
    /*{
        return new CustomeAuthenticationProvider();
    }*/

}
