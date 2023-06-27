package com.security.service;

import com.security.entity.Customer;

import com.security.jwt.JwtUtil;
import com.security.pojo.Customerpojo;
import com.security.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtutil;


    public void saveCustomer(Customerpojo customerpojo)
    {
        customerpojo.setPassword(encoder.encode(customerpojo.getPassword()));
        Customer customer = this.modelMapper.map(customerpojo, Customer.class);
        this.customerRepository.save(customer);
    }

    public boolean validateUser(Customerpojo customerpojo)
    {
        Customer customer = customerRepository.findByEmail(customerpojo.getEmail());
        if(customer == null)
            return false;
        else
        {
            log.info("customer is not null");
            if(encoder.matches(customerpojo.getPassword(),customer.getPassword()))
                return true;
        }
        return false;

    }


    public String generateToken(Customerpojo pojo)
    {
       String token = jwtutil.generateAccessToken(pojo);
       return token;
    }

    @CachePut(cacheNames = "cache1",key = "'#key'")
    public void updateToken(String email,String token)
    {
        Customer customer = customerRepository.findByEmail(email);
        customer.setToken(token);
        customerRepository.save(customer);
    }

    @Cacheable(cacheNames = "cache1",key = "'#key'")
    public Customerpojo getCustomer(String email)
    {
        Customer customer = customerRepository.findByEmail(email);
        log.info(customer.toString());
        return modelMapper.map(customer, Customerpojo.class);
    }
}
