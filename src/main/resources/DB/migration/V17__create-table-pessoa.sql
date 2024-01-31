create table pessoa
(
    id               int primary key,
    data_cadastro    timestamp not null,
    fim_vigencia     timestamp,
    data_atualizacao timestamp,
    situacao         varchar(15)
);