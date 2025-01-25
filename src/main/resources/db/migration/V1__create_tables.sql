CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(30) NOT NULL UNIQUE,
    password VARCHAR(30) NOT NULL,
    email    VARCHAR(30) UNIQUE
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(30) NOT NULL
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);