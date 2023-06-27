package com.customer.pojo;

import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChangePasswordPojo {

    private long id;
    private String password;
}
