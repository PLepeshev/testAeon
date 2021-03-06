package ru.lepetr.payment_service.db;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "users")
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String login;
    @Column(nullable = false)
    private String passwordEnc;
    @Column
    private String token;
    @Column
    private LocalDateTime authErrTime;
    @Column
    private BigDecimal accSumUsd;

    public UserEntity(String login, String passwordEnc) {
        this.login = login;
        this.passwordEnc = passwordEnc;
    }
}
