package com.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "addressId")
    private long addressId;
    @Column(name = "al1")
    private String addLine1;
    @Column(name = "al2")
    private String addLine2;
    @Column(name = "state")
    private String state;
    @Column(name = "country")
    private String country;
    @Column(name = "city")
    private String city;

    @Column(name = "pin")
    private String pinCode;

    /*@OneToOne(mappedBy = "address",cascade = CascadeType.ALL)
    private User user;*/

}
