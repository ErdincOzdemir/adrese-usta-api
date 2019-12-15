package com.adreseusta.api.user.service.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String passwordAgain;
}
