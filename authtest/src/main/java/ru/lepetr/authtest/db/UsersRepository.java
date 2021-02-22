package ru.lepetr.authtest.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<UserEntity,Integer> {
    UserEntity findByLogin(String login);
    UserEntity findByToken(String token);
}
