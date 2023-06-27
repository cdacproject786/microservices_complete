package com.security.controller;

import com.security.pojo.Customerpojo;
import com.security.pojo.UserRegister;
import com.security.service.CustomerService;
import com.security.service.ExternalApiService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@Slf4j
public class VendorController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ExternalApiService apiService;

    @PostMapping("/savevendor")
    public ResponseEntity<?> saveVendor(@RequestBody UserRegister userRegister)
    {
        Customerpojo customerpojo = this.modelMapper.map(userRegister, Customerpojo.class);
        this.customerService.saveCustomer(customerpojo);
        this.apiService.saveExternalVendor(userRegister);
        return new ResponseEntity<>(null,HttpStatus.CREATED);
    }


}
