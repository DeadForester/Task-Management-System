create table if not exists users
(
    id       bigserial primary key,
    name     varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null

);

create table if not exists tasks
(
    id              bigserial primary key,
    title           varchar(255)  not null,
    description     varchar(4096) null,
    status          varchar(255)  not null,
    expiration_date timestamp     null
);


create table if not exists user_tasks
(
    user_id bigint not null,
    task_id bigint not null,
    primary key (user_id, task_id),
    constraint fk_user_tasks_users foreign key (user_id) references users (id) on delete cascade on update no action,
    constraint fk_user_tasks_tasks foreign key (task_id) references tasks (id) on delete cascade on update no action
);

create table if not exists users_roles
(
    user_id bigint       not null,
    role    varchar(255) not null,
    primary key (user_id, role),
    constraint fk_user_roles_users foreign key (user_id) references users (id) on delete cascade on update no action
);