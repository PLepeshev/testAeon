package ru.lepetr.payment_service.rest;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lepetr.payment_service.db.RepositoryHelper;

@SpringBootTest
class AuthServiceTest {
    @Autowired
    AuthService authService;
    @Autowired
    RepositoryHelper repositoryHelper;


    @Test
    void getTokenAuthOk() {
        Response response = authService.getToken("user1", "123456");
        Assertions.assertEquals(Response.Status.AUTH_OK, response.status);
    }

    @Test
    void getTokenAuthErr() {
        Response responseEntity = authService.getToken("user1", "1234567");
        Assertions.assertEquals(Response.Status.AUTH_ERR, (responseEntity.status));
    }

    @Test
    void removeTokenOk() {
        String token = repositoryHelper.getUserToken("user1");
        Response response = authService.removeToken(token);
        Assertions.assertEquals(Response.Status.REMOVE_TOKEN_OK, response.status);
        String tokenNew = repositoryHelper.getUserToken("user1");
        Assertions.assertNotEquals(token, tokenNew);
    }

    @SneakyThrows
    @Test
    void authPossible() {
        Assertions.assertEquals(Response.Status.AUTH_ERR, authService.getToken("user1", "1234567").status);
        Assertions.assertEquals(false, repositoryHelper.checkAuthPossible("user1"));
        Thread.sleep(2000);
        Assertions.assertEquals(true, repositoryHelper.checkAuthPossible("user1"));
        Assertions.assertEquals(Response.Status.AUTH_OK, authService.getToken("user1", "123456").status);
    }
}