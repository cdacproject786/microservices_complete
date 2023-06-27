package com.security.pojo;

import lombok.*;

import java.io.Serializable;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Customerpojo implements Serializable {
    private long customerId;
    private String email;
    private String password;
    private String token;
}

