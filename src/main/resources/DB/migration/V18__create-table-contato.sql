create table contato
(
    id            int primary key,
    id_pessoa     int       not null,
    data_cadastro timestamp not null,
    fim_vigencia  timestamp
);