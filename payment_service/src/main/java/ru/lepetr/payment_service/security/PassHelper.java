package ru.lepetr.payment_service.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PassHelper {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean passMatches(String pass, String storedEncPass) {
        return passwordEncoder.matches(pass, storedEncPass);
    }
}
