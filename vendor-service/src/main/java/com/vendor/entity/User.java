package com.vendor.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vendor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uid")
    private long userId;
    @Column(name = "fname")
    private String firstName;
    @Column(name = "lname")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Column(name = "pwd")
    private String password;
    @Column(name = "occup")
    private String occupation;
    @Column(name = "pan")
    private String panCard;
    @Column(name = "adhaar")
    private String adhaarNo;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address", referencedColumnName = "addressId")
    private Address address;
}

