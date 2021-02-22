# testAeon
Test task for AEON
## Задача:
Сделать API с возможностью авторизации пользователя и совершения платежа только после успешной авторизации. Должны быть 3 endpoint'а: 
login (вводим логин и пароль, при успехе выдает токен), logout (делает токен недействительным) и payment (при добавлении пользователя в БД ставим баланс 8 USD, сама операция позволяет снимать с баланса пользователя 1.1 USD при каждом вызове, 
все совершенные платежи хранятся в БД). Сделанный проект надо выгрузить в репозиторий на Github.

### Требования к функционалу (авторизация):
- если логин/пароль неправильные - выводим ошибку
- одновременная поддержка нескольких сессий пользователя
- не хранить пароли в базе в открытом виде
- защита от брутфорса (подбора пароля)

### Требования к функционалу (платеж):
- защита от ошибочных списаний (изоляция транзакций)
- отсутствие ошибок округления
- корректное хранение и операции с финансовыми данными

### Требования к коду:
- конкретный стек (фреймворки и библиотеки) не принципиален
- простая реализация логики и БД

## Реализация:
- БД используется H2, создается и заполняется данными при старте сервиса.
### предустановленные значения в БД пользователей:
- use1 123456
- user2 1234567

## 3 endPoint-а
### login
принимает логин и пароль пользователя, возвращает в классе Response токен и статус выполнения метода
### logout
принимает токен пользователя, возвращает в классе Response статус выполнения операции
### payment 
принимает токен пользователя, возвращает в классе Response статус операции и текущий баланс на счете

