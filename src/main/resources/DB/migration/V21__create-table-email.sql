create table email
(
    id               int primary key,
    email            varchar(100),
    tipo             varchar(15),
    data_cadastro    timestamp not null,
    fim_vigencia     timestamp,
    data_atualizacao timestamp,
    id_contato       int       not null

);