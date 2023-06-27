package com.customer.pojo;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressChangePojo {

    private long userId;
    private long addressId;
    private String addLine1;
    private String addLine2;
    private String state;
    private String country;
    private String city;
    private String pinCode;
}
