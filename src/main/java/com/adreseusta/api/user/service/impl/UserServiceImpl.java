package com.adreseusta.api.user.service.impl;

import com.adreseusta.api.common.service.impl.BaseServiceImpl;
import com.adreseusta.api.config.JwtTokenUtil;
import com.adreseusta.api.user.exception.WrongUsernameOrPasswordException;
import com.adreseusta.api.user.mapper.UserMapper;
import com.adreseusta.api.user.persistence.entity.Login;
import com.adreseusta.api.user.persistence.entity.User;
import com.adreseusta.api.user.persistence.repository.LoginRepository;
import com.adreseusta.api.user.persistence.repository.UserRepository;
import com.adreseusta.api.user.service.UserService;
import com.adreseusta.api.user.service.dto.LoginDTO;
import com.adreseusta.api.user.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl extends BaseServiceImpl implements UserService {

    private UserRepository userRepository;
    private LoginRepository loginRepository;
    private UserMapper userMapper;
    private JwtTokenUtil jwtTokenUtil;

    public UserServiceImpl(UserRepository userRepository, LoginRepository loginRepository, UserMapper userMapper, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.loginRepository = loginRepository;
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.toUser(userDTO);
        User resultUser = userRepository.save(user);
        return userMapper.toUserDTO(resultUser);
    }

    @Override
    public UserDTO getUser(LoginDTO loginDTO) throws WrongUsernameOrPasswordException {
        Optional<User> userQuery = userRepository.findUserByPasswordAndEmailOrPhoneNumber(loginDTO.getPassword(), loginDTO.getPhoneNumberEmail(), loginDTO.getPhoneNumberEmail());

        if (userQuery.isPresent()) {
            User user = userQuery.get();
            String clientIpAddress = getClientIpAddress();
            Login login = new Login(user, LocalDateTime.now(), clientIpAddress);
            loginRepository.save(login);

            UserDTO userDTO = userMapper.toUserDTO(user);
            userDTO.setToken(jwtTokenUtil.generateToken(loginDTO));

            return userDTO;
        }

        throw new WrongUsernameOrPasswordException();
    }
}
