insert into users (name, username, password)
values ('Игорь Петров', 'igor@gmail.com', '$2a$10$H88dHjT.0T2NR79LECY3Aeo42lHZsWv6qEUPfm7qau5VAHqs7dMiu'),
       ('Петр Смирнов', 'petr@gmail.com', '$2a$10$H88dHjT.0T2NR79LECY3Aeo42lHZsWv6qEUPfm7qau5VAHqs7dMiu');

insert into tasks (title, description, status, expiration_date)
values ('Сделать отчет', null, 'TODO', '2025-02-03 12:00'),
       ('Предоставить данные', 'Очень быстро. Пожалуйста', 'IN_PROGRESS', '2025-02-01 09:58'),
       ('Предоставить юристам информацию о должниках', null, 'DONE', null),
       ('Выгрузить отчет за 14.03.2024 - 21.01.2025 для бухгалтерии по безналу', 'Описание задачи', 'TODO', '2025-02-01 11:30');

insert into user_tasks(task_id, user_id)
values (1, 2),
       (2, 2),
       (3, 1),
       (4, 1);

insert into users_roles(user_id, role)
values (1, 'ROLE_ADMIN'),
       (1, 'ROLE_USER'),
       (2, 'ROLE_USER');