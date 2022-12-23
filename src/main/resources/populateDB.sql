DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM claim_parts;
DELETE FROM claims;

INSERT INTO users
VALUES (1, 'Vassily Petrov', 'vpetrov@jr.com', '1234',  true, now(), 31),
       (2, 'Pjotr Vasechkin', 'pvasechkin@jr.com', '1234',  true, now(), 15);

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADVISER', 2),
       ('USER', 2);

INSERT INTO dealers_users (dealer_code, users_id)
VALUES (92320, 1),
       (92319, 2);