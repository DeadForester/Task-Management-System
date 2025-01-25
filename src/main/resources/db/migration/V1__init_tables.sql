create table users
(
    id       bigserial Primary Key,
    username varchar(30) not null unique,
    password varchar(30) not null,
    email    varchar(30) unique
);

create table roles
(
    id   serial PRIMARY KEY,
    name varchar(30) not null
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '123456', 'user@mail.ru'),
       ('admin', '123456', 'admin@mail.ru');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);