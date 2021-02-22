package ru.lepetr.authtest.db;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.lepetr.authtest.rest.Response;

import java.math.BigDecimal;

@SpringBootTest
@Slf4j
public class RepositoryHelperTest {
    @Autowired
    RepositoryHelper repositoryHelper;

    @Test
    void payment() {
        Response response = repositoryHelper.doPayment(repositoryHelper.getUserToken("user1"));
        Assertions.assertEquals(Response.Status.PAYMENT_OK, response.getStatus());
        Response response1 = new Response();
        for (int i = 0; i < 8; i++) {
            response1 = repositoryHelper.doPayment(repositoryHelper.getUserToken("user1"));
        }
        Assertions.assertTrue(response1.getBalance().compareTo(BigDecimal.ZERO) > 0);
    }


}