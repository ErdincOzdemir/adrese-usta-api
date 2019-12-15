package com.adreseusta.api.user.api.response;

import com.adreseusta.api.common.api.response.BaseResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterResponse extends BaseResponse {
    @ApiModelProperty(name = "firstName", notes = "first name of user")
    private String firstName;

    @ApiModelProperty(name = "lastName", notes = "last name of user")
    private String lastName;

    @ApiModelProperty(name = "email", notes = "email of user")
    private String email;

    @ApiModelProperty(name = "phoneNumber", notes = "phone number of user")
    private String phoneNumber;
}
