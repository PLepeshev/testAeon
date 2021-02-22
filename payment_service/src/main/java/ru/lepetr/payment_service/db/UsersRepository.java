package ru.lepetr.payment_service.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface UsersRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByLogin(String login);

    UserEntity findByToken(String token);
}
