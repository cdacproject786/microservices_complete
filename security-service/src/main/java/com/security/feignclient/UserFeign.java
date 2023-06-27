package com.security.feignclient;

import com.security.pojo.UserRegister;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "CUSTOMER-SERVICE")
public interface UserFeign {

    @PostMapping("/customer/save")
    public UserRegister saveUser(@RequestBody UserRegister user);
}
