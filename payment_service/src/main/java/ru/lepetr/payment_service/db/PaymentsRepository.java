package ru.lepetr.payment_service.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentsRepository extends CrudRepository<PaymentEntity, Integer> {
    List<PaymentEntity> findByLogin(String login);
}
