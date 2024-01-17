create table cliente
(
    id               serial primary key not null,
    nome             varchar(200)       not null,
    email            varchar(200)       not null,
    data_inicio      timestamp          not null,
    data_fim         timestamp,
    data_atualizacao timestamp
);