package com.vendor.controller;

import com.vendor.pojo.VendorRegister;
import com.vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    @Autowired
    private VendorService vendorService;

    @PostMapping("/save")
    public ResponseEntity<?> saveVendor(@RequestBody VendorRegister vendorRegister)
    {
        vendorService.saveVendor(vendorRegister);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


}
