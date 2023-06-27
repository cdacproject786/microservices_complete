package com.customer.controller;

import com.customer.entity.User;
import com.customer.pojo.AddressChangePojo;
import com.customer.pojo.ChangePasswordPojo;
import com.customer.pojo.UserRegister;
import com.customer.repository.UserRepository;
import com.customer.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Slf4j
public class UserController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/save")
    public UserRegister saveUser(@RequestBody UserRegister user)
    {
        return customerService.saveUser(user);
    }

    @GetMapping("/users")
    public ResponseEntity<?> message()
    {
        List<UserRegister> list = this.customerService.getAllUsers();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping(path = "/changepassword", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
                                                produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStudent(@ModelAttribute ChangePasswordPojo pojo)
    {
        UserRegister changePassword = this.customerService.changePassword(pojo.getId(), pojo.getPassword());
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping(path = "/changeAddress", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE},
                                                        produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> changeAddress(@ModelAttribute AddressChangePojo pojo)
    {
        UserRegister updated = this.customerService.updateAddress(pojo.getAddressId(), pojo);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
