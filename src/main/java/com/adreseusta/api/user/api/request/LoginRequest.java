package com.adreseusta.api.user.api.request;

import com.adreseusta.api.common.api.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class LoginRequest extends BaseRequest {
    @NotNull
    @ApiModelProperty(name = "phoneNumberEmail", notes = "phone number or email of user", required = true)
    private String phoneNumberEmail;

    @NotNull
    @ApiModelProperty(name = "password", notes = "password", required = true)
    @Length(min = 8, max = 16)
    private String password;
}
