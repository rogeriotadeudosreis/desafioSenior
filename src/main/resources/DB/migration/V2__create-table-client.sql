create table cliente
(
    id              serial primary key not null,
    nome            varchar(200)       not null,
    email           varchar(200)       not null,
    inicio_vigencia timestamp          not null,
    fim_vigencia    timestamp,
    atualizacao     timestamp
);