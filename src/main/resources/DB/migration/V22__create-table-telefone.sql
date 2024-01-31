create table telefone
(
    id               int primary key,
    telefone         varchar(12),
    ddd              varchar(3),
    ddi              varchar(3),
    tipo             varchar(20),
    id_contato       int       not null,
    data_cadastro    timestamp not null,
    fim_vigencia     timestamp,
    data_atualizacao timestamp
);