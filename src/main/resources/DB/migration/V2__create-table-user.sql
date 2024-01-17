create table usuario
(
    id       Text primary key not null,
    login    text             not null,
    password text             not null,
    role     text             not null
);