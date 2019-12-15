package com.adreseusta.api.user.mapper;

import com.adreseusta.api.user.api.request.LoginRequest;
import com.adreseusta.api.user.api.request.RegisterRequest;
import com.adreseusta.api.user.api.response.LoginResponse;
import com.adreseusta.api.user.api.response.RegisterResponse;
import com.adreseusta.api.user.persistence.User;
import com.adreseusta.api.user.service.dto.LoginDTO;
import com.adreseusta.api.user.service.dto.UserDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(RegisterRequest registerRequest);

    RegisterResponse toRegisterResponse(UserDTO userDTO);

    User toUser(UserDTO userDTO);

    UserDTO toUserDTO(User user);

    LoginResponse toLoginResponse(UserDTO userDTO);

    LoginDTO toLoginDTO(LoginRequest loginRequest);
}
