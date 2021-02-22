package ru.lepetr.payment_service.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(isolation = Isolation.READ_COMMITTED)
public interface PaymentsRepository extends CrudRepository<PaymentEntity, Integer> {
    List<PaymentEntity> findByLogin(String login);
}
