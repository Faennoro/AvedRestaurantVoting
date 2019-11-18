DELETE FROM user_roles;
DELETE FROM USER_VOTE_HISTORY;
DELETE FROM users;
DELETE FROM dishes;
DELETE FROM RESTAURANT_VOTE_HISTORY;
DELETE FROM RESTAURANTS;


ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
('User', 'user@yandex.ru', '{noop}password'),
('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id) VALUES
('ROLE_USER', 100000),
('ROLE_ADMIN', 100001),
('ROLE_USER', 100001);

INSERT INTO restaurants (name, address, date)
VALUES ('KFC', 'ул. Первая 10', '2019-11-17'),
       ('Royal', 'ул. Вторая 12', '2019-11-17'),
       ('KFC', 'ул. Первая 10', '2019-11-18'),
       ('Вилка-Ложка', 'ул. Пятая 13', '2019-11-18');

INSERT INTO dishes (description, price, restaurant_id)
VALUES ('Бургер', 200, 100002),
       ('Ролл', 120, 100002),
       ('Моллюски', 800, 100003),
       ('Креветки', 520, 100003),
       ('Бургер', 200, 100004),
       ('Ролл', 120, 100004),
       ('Салат', 90, 100005),
       ('Пюре', 70, 100005);

INSERT INTO RESTAURANT_VOTE_HISTORY (DATE, VOTES, RESTAURANT_ID)
VALUES ('2019-11-17', 1, 100002),
       ('2019-11-17',1,100003),
       ('2019-11-18', 2, 100004),
       ('2019-11-18',0,100005);

INSERT INTO USER_VOTE_HISTORY(date, USER_ID, RESTAURANT_ID)
values ('2019-11-17', 100000, 100002),
       ('2019-11-17',100001,100003),
       ('2019-11-18', 100000, 100004),
       ('2019-11-18',100001,100005);