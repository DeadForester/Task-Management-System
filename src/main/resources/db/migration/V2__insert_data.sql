insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '123456', 'user@mail.ru'),
       ('admin', '123456', 'admin@mail.ru');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);