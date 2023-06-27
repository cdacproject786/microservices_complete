package com.customer.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "user")
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements Serializable {
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
    @JoinColumn(name = "address",referencedColumnName = "addressId")
    private Address address;

    public User(String firstName, String lastName, String email, String phone, String password, String occupation, String panCard, String adhaarNo, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.occupation = occupation;
        this.panCard = panCard;
        this.adhaarNo = adhaarNo;
        this.address = address;
    }
}
