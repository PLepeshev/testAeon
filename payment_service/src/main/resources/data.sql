DROP TABLE IF EXISTS users;
CREATE TABLE users (
    id   IDENTITY NOT NULL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    password_enc VARCHAR(250) NOT NULL,
    token VARCHAR(250),
    auth_err_time TIMESTAMP,
    acc_sum_usd DECIMAL(19,4),
    CONSTRAINT uc_login UNIQUE (login),
    CONSTRAINT uc_token UNIQUE (token)
);

DROP TABLE IF EXISTS payments;
CREATE TABLE payments (
    id   IDENTITY NOT NULL PRIMARY KEY,
    login VARCHAR(50) NOT NULL,
    date_pay TIMESTAMP,
    sum_pay DECIMAL(19,4),
    FOREIGN KEY (login) REFERENCES users(login)
);


INSERT INTO users (id,login, password_enc, acc_sum_usd) VALUES
  (1,'user1', '$2a$10$ucMiWSrykZdXlVJxy6PGHukHrXDrWEljWCs5SGp73KRz5SLdoqIn.', 8),--123456
  (2,'user2', '$2a$10$l1vb1fo3f3KqnB/mAOD4yePtprokstj7H7xK2CpL1jXXPkK8yRvPO', 8);--1234567
