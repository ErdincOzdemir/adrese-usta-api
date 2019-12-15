package com.adreseusta.api.user.exception;

import com.adreseusta.api.common.exception.type.DataNotFoundException;

public class WrongUsernameOrPasswordException extends DataNotFoundException {
    public WrongUsernameOrPasswordException() {
        super(UserErrorType.WRONG_USERNAME_OR_PASSWORD.getCode(), "user.wrong_username_or_password");
    }
}
