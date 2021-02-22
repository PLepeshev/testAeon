package ru.lepetr.authtest.security;

import java.util.UUID;

public class TokenHelper {
    public static String getToken() {
        return UUID.randomUUID().toString();
    }
}
