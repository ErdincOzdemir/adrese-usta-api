package com.adreseusta.api.user.api.request;

import com.adreseusta.api.common.api.request.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RegisterRequest extends BaseRequest {

    @ApiModelProperty(name = "firstName", notes = "first name of user")
    private String firstName;

    @ApiModelProperty(name = "lastName", notes = "last name of user")
    private String lastName;

    @NotNull
    @ApiModelProperty(name = "email", notes = "email of user", required = true)
    private String email;

    @NotNull
    @ApiModelProperty(name = "phoneNumber", notes = "phone number of user", required = true)
    private String phoneNumber;

    @NotNull
    @ApiModelProperty(name = "password", notes = "password", required = true)
    @Length(min = 8, max = 16)
    private String password;

    @NotNull
    @ApiModelProperty(name = "passwordAgain", notes = "password again", required = true)
    @Length(min = 8, max = 16)
    private String passwordAgain;
}
