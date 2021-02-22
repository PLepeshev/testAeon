package ru.lepetr.authtest.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.lepetr.authtest.rest.Response;
import ru.lepetr.authtest.security.TokenHelper;

import java.math.BigDecimal;
import java.math.MathContext;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class RepositoryHelper {
    UsersRepository usersRepository;
    private final Duration TIMEOUT_AUTH_ERR = Duration.ofMillis(2000);
    private final BigDecimal PAYMENT_AMOUNT = new BigDecimal(1.1d);

    public RepositoryHelper(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Transactional
    public String getUserToken(String login) {
        UserEntity userEntity = usersRepository.findByLogin(login);
        if (Objects.isNull(userEntity.getToken())) {
            userEntity.setToken(TokenHelper.getToken());
        }
        return userEntity.getToken();
    }

    @Transactional
    public boolean deleteToken(String token) {
        UserEntity userEntity = usersRepository.findByToken(token);
        if (Objects.nonNull(userEntity) && Objects.nonNull(userEntity.getToken())) {
            userEntity.setToken(null);
            return true;
        }
        return false;
    }

    public String getUserPassEnc(String login) {
        UserEntity userEntity = usersRepository.findByLogin(login);
        if (Objects.nonNull(userEntity)) {
            return userEntity.getPasswordEnc();
        }
        return null;
    }

    @Transactional
    public void setAuthLastErrTime(String login) {
        UserEntity userEntity = usersRepository.findByLogin(login);
        if (Objects.nonNull(userEntity)) {
            userEntity.setAuthErrTime(LocalDateTime.now());
        }
    }

    @Transactional
    public boolean checkAuthPossible(String login) {
        UserEntity userEntity = usersRepository.findByLogin(login);
        if (Objects.nonNull(userEntity)) {
            if (Objects.isNull(userEntity.getAuthErrTime())) {
                return true;
            }
            return Duration.between(userEntity.getAuthErrTime(), LocalDateTime.now()).compareTo(TIMEOUT_AUTH_ERR) > 0;
        }
        return false;
    }

    @Transactional
    public Response doPayment(String token) {
        UserEntity userEntity = usersRepository.findByToken(token);
        if (Objects.isNull(userEntity)) {
            Response response = new Response();
            response.setStatus(Response.Status.AUTH_ERR);
            return response;
        }
        if (Objects.nonNull(userEntity.getAccSumUsd()) && userEntity.getAccSumUsd().compareTo(PAYMENT_AMOUNT) >= 0) {
            userEntity.setAccSumUsd(userEntity.getAccSumUsd().add(PAYMENT_AMOUNT.negate()).round(new MathContext(2)));
            Response response = new Response();
            response.setBalance(userEntity.getAccSumUsd());
            response.setStatus(Response.Status.PAYMENT_OK);
            return response;
        } else {
            Response response = new Response();
            response.setBalance(userEntity.getAccSumUsd());
            response.setStatus(Response.Status.PAYMENT_NOT_ENOUGH);
            return response;
        }


    }
}
