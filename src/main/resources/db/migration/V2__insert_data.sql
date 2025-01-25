insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@mail.ru'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@mail.ru'),
       ('Bob','$2a$10$hptN6IZWILomGVZBt3bph.odSGuoW8VhtF5eZAXdoSQxQB5VWTCfG','Bob@gmail.com'),
       ('Mike','$2a$10$Y0Gu.77VVPP2yiLmRGa6OOraD5RMdF4dm/4lpNbCawVcdJsYN8x.u','Mike@gmail.com');

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');


insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2),
       (3, 1),
       (4, 1);