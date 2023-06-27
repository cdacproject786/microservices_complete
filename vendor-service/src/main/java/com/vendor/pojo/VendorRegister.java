package com.vendor.pojo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorRegister {

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
