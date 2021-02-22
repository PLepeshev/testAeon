package ru.lepetr.payment_service.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PassHelperTest {

    @Test
    void passMatches() {
        PassHelper passHelper = new PassHelper();
        assertTrue(passHelper.passMatches("123456", "$2a$10$ucMiWSrykZdXlVJxy6PGHukHrXDrWEljWCs5SGp73KRz5SLdoqIn."));
    }
}