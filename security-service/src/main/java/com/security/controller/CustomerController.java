package com.security.controller;

import com.security.pojo.Customerpojo;
import com.security.pojo.UserRegister;
import com.security.service.CustomerService;
import com.security.service.ExternalApiService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExternalApiService apiService;

    @CircuitBreaker(name = "userVendorBreaker",fallbackMethod = "userVendorFallback")

    //creating fallback method for circuit breaker
    public ResponseEntity<?> userVendorFallback(@RequestBody UserRegister register, Exception ex)
    {
        log.info("Falback is executed because service is down");
        return new ResponseEntity<>(null, HttpStatus.SERVICE_UNAVAILABLE);
    }


    @PostMapping("/saveuser")
    public ResponseEntity<?> saveCustomer(@RequestBody UserRegister userRegister)
    {
        Customerpojo customerpojo = this.modelMapper.map(userRegister, Customerpojo.class);
        this.customerService.saveCustomer(customerpojo);
        //log.info("Item saved to the security db");
        this.apiService.saveUserExternalUser(userRegister);
        //log.info("Item saved on the external api");
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

/*    @PostMapping("/savevendor")
    public ResponseEntity<?> saveVendor(@RequestBody UserRegister userRegister)
    {
        Customerpojo customerpojo = this.modelMapper.map(userRegister, Customerpojo.class);
        this.customerService.saveCustomer(customerpojo);
        this.apiService.saveExternalVendor(userRegister);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Customerpojo pojo)
    {
        boolean valid = customerService.validateUser(pojo);
        log.info("is the user valid: "+valid);
        if(valid)
        {
            //code to generate access token
            String token = customerService.generateToken(pojo);
            log.info("JWT token: "+token);
            customerService.updateToken(pojo.getEmail(),token);
            pojo.setToken(token);
            pojo.setPassword(null);
            log.info("before returning the response");
            return new ResponseEntity<>(pojo,HttpStatus.OK);
        }
        else
        {
            log.info("false");
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
    }

    /*@GetMapping("/sample")
    public String getResponse() throws InterruptedException {
        return "Response to check for load shedding";
    }*/
    @GetMapping("/token/{email}")
    public Customerpojo getCustomer(@PathVariable("email") String email)
    {
        return customerService.getCustomer(email);
    }
}
