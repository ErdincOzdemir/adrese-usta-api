package com.adreseusta.api.user.api;

import com.adreseusta.api.user.api.request.LoginRequest;
import com.adreseusta.api.user.api.request.RegisterRequest;
import com.adreseusta.api.user.api.response.LoginResponse;
import com.adreseusta.api.user.api.response.RegisterResponse;
import com.adreseusta.api.user.exception.WrongUsernameOrPasswordException;
import com.adreseusta.api.user.mapper.UserMapper;
import com.adreseusta.api.user.service.UserService;
import com.adreseusta.api.user.service.dto.LoginDTO;
import com.adreseusta.api.user.service.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "user controller")
@RequestMapping("/user")
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @ApiOperation(value = "register new user")
    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Validated @RequestBody RegisterRequest registerRequest) {
        UserDTO userDTO = userMapper.toUserDTO(registerRequest);
        UserDTO resultUserDTO = userService.createUser(userDTO);
        return new ResponseEntity<>(userMapper.toRegisterResponse(resultUserDTO), HttpStatus.CREATED);
    }

    @ApiOperation(value = "login user")
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Validated @RequestBody LoginRequest loginRequest) throws WrongUsernameOrPasswordException {
        LoginDTO loginDTO = userMapper.toLoginDTO(loginRequest);
        UserDTO userDTO = userService.getUser(loginDTO);
        return new ResponseEntity<>(userMapper.toLoginResponse(userDTO), HttpStatus.OK);
    }
}
