package com.adreseusta.api.user.service.dto;

import com.adreseusta.api.common.service.dto.BaseDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO extends BaseDTO {
    private String phoneNumberEmail;
    private String password;
}
