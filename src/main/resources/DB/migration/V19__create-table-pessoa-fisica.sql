create table pessoa_fisica
(
    id               int primary key,
    ID_PESSOA        int       not null,
    CPF              varchar(11) not null ,
    NOME             varchar(200) not null ,
    NOME_SOCIAL      varchar(200) ,
    RG               varchar(20) ,
    PASSAPORTE       varchar(10),
    DATA_CADASTRO    timestamp not null,
    FIM_VIGENCIA     timestamp,
    DATA_ATUALIZACAO timestamp
);