package com.adreseusta.api.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  UserErrorType {

    WRONG_USERNAME_OR_PASSWORD(1000L)
    ;

    private Long code;
}
