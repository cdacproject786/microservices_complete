package com.customer.service;

import com.customer.entity.Address;
import com.customer.entity.User;
import com.customer.exception.UserNotFoundException;
import com.customer.pojo.AddressChangePojo;
import com.customer.pojo.AddressPojo;
import com.customer.pojo.UserRegister;
import com.customer.repository.AddressRepository;
import com.customer.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    public UserRegister saveUser(UserRegister userRegister)
    {
        User user = this.modelMapper.map(userRegister, User.class);
        Address address = this.modelMapper.map(userRegister, Address.class);
        user.setAddress(address);
        User savedUser = this.userRepository.save(user);
        return modelMapper.map(savedUser,UserRegister.class);
    }

    @Cacheable(cacheNames = "cache2", key = "'#key2'")
    public List<UserRegister> getAllUsers()
    {
        try {
            List<User> userList = this.userRepository.findAll();
            List<UserRegister> userRegisterList = new LinkedList<>();

            for(int i=0; i<userList.size();i++)
             userRegisterList.add(this.modelMapper.map(userList.get(i),UserRegister.class));

            return userRegisterList;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

   @CachePut(cacheNames = "cache2",key = "'#key2'")
    public UserRegister changePassword(long id, String newPassword)
   {
      User foundUser = this.userRepository.findById(id).get();
      foundUser.setPassword(newPassword);
      User savedUser = this.userRepository.save(foundUser);
      return this.modelMapper.map(savedUser, UserRegister.class);
   }

   @CachePut(cacheNames = "cache2", key = "'#key2'")
    public UserRegister updateAddress(long id, AddressChangePojo addressToModify)
   {
       User founduser = this.userRepository.findById(id).get();
       Address foundAddress = this.addressRepository.getReferenceById(id);
       Address newAddress = this.modelMapper.map(addressToModify,Address.class);
       newAddress.setAddressId(foundAddress.getAddressId());
       founduser.setAddress(this.addressRepository.save(newAddress));
       User savedUser = this.userRepository.save(founduser);
       return this.modelMapper.map(savedUser,UserRegister.class);
   }
}
