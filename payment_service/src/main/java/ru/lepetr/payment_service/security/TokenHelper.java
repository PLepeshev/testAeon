package ru.lepetr.payment_service.security;

import java.util.UUID;

public class TokenHelper {
    public static String getToken() {
        return UUID.randomUUID().toString();
    }
}
