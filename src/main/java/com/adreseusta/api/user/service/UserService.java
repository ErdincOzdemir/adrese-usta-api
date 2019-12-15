package com.adreseusta.api.user.service;

import com.adreseusta.api.user.exception.WrongUsernameOrPasswordException;
import com.adreseusta.api.user.service.dto.LoginDTO;
import com.adreseusta.api.user.service.dto.UserDTO;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUser(LoginDTO loginDTO) throws WrongUsernameOrPasswordException;
}
