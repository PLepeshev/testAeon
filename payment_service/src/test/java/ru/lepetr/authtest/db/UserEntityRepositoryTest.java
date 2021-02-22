package ru.lepetr.authtest.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class UserEntityRepositoryTest {
    @Autowired
    UsersRepository usersRepository;

    @Test
    public void testFindByLogin() {
        assertNotNull(Objects.nonNull(usersRepository.findByLogin("user1")));
    }

    @Test
    public void testUniqueConstr() {
        assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
            UserEntity userEntity = new UserEntity("user1", "hkjhkj");
            usersRepository.save(userEntity);
        });
    }

}