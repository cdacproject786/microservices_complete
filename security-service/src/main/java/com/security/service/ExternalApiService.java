package com.security.service;

import com.security.feignclient.UserFeign;
import com.security.feignclient.VendorFeign;
import com.security.pojo.UserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ExternalApiService {

    @Autowired
    private UserFeign userFeign;

    @Autowired
    private VendorFeign vendorFeign;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void saveUserExternalUser(UserRegister userRegister)
    {
        userRegister.setPassword(passwordEncoder.encode(userRegister.getPassword()));
        this.userFeign.saveUser(userRegister);
    }

    public void saveExternalVendor(UserRegister register)
    {
        register.setPassword(passwordEncoder.encode(register.getPassword()));
        vendorFeign.saveVendor(register);
    }
}
