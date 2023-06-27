package com.security.feignclient;

import com.security.pojo.UserRegister;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "VENDOR-SERVICE")
public interface VendorFeign {

    @PostMapping("/vendor/save")
    public ResponseEntity<?> saveVendor(@RequestBody UserRegister vendorRegister);
}
