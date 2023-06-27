package com.vendor.service;

import com.vendor.entity.User;
import com.vendor.pojo.VendorRegister;
import com.vendor.repository.VendorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ModelMapper modelMapper;


    public void saveVendor(VendorRegister pojo)
    {
        User user = modelMapper.map(pojo, User.class);
        this.vendorRepository.save(user);
    }
}
