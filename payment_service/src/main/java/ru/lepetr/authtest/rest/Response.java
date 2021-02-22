package ru.lepetr.authtest.rest;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class Response {
    String token;
    Status status;
    BigDecimal balance;

    public enum Status {
        AUTH_OK,
        AUTH_ERR,
        USER_NOT_FOUND,
        REMOVE_TOKEN_OK,
        REMOVE_TOKEN_ERR,
        PAYMENT_OK,
        PAYMENT_NOT_ENOUGH
    }
}
