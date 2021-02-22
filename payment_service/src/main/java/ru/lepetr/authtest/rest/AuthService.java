package ru.lepetr.authtest.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lepetr.authtest.db.RepositoryHelper;
import ru.lepetr.authtest.security.PassHelper;

import java.util.Objects;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class AuthService {
    RepositoryHelper repositoryHelper;
    PassHelper passHelper;

    public AuthService(RepositoryHelper repositoryHelper, PassHelper passHelper) {
        this.repositoryHelper = repositoryHelper;
        this.passHelper = passHelper;
    }

    @GetMapping(value = "/login", produces = APPLICATION_JSON_VALUE)
    Response getToken(@RequestParam(name = "user") String login, @RequestParam(name = "password") String password) {
        if (!repositoryHelper.checkAuthPossible(login)) {
            Response response = new Response();
            response.status = Response.Status.AUTH_ERR;
            return response;
        }
        String passEnc = repositoryHelper.getUserPassEnc(login);
        if (Objects.nonNull(passEnc)) {
            if (passHelper.passMatches(password, passEnc)) {
                Response response = new Response();
                response.status = Response.Status.AUTH_OK;
                response.token = repositoryHelper.getUserToken(login);
                return response;
            } else {
                Response response = new Response();
                response.status = Response.Status.AUTH_ERR;
                repositoryHelper.setAuthLastErrTime(login);
                return response;
            }
        } else {
            Response response = new Response();
            response.status = Response.Status.USER_NOT_FOUND;
            return response;
        }
    }

    @GetMapping(value = "/logout", produces = APPLICATION_JSON_VALUE)
    Response removeToken(@RequestParam(name = "token") String token) {
        if (repositoryHelper.deleteToken(token)) {
            Response response = new Response();
            response.status = Response.Status.REMOVE_TOKEN_OK;
            return response;
        } else {
            Response response = new Response();
            response.status = Response.Status.REMOVE_TOKEN_ERR;
            return response;
        }
    }

    @GetMapping(value = "/payment", produces = APPLICATION_JSON_VALUE)
    Response doPayment(@RequestParam(name = "token") String token) {
        return repositoryHelper.doPayment(token);
    }
}
