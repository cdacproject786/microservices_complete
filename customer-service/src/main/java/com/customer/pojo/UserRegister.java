package com.customer.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserRegister implements Serializable {

    private long userId;
    private long addressId;
    private String addLine1;
    private String addLine2;
    private String state;
    private String country;
    private String city;
    private String pinCode;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private String occupation;
    private String panCard;
    private String adhaarNo;

}
