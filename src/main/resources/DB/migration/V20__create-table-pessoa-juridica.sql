create table pessoa_juridica
(
    id int primary key,
    razao_social varchar(200) not null ,
    nome_fantasia varchar(200) not null ,
    cnpj varchar(14),
    inscricao_estdual varchar(30),
    inscricao_municipal varchar(30),
    id_pessoa int not null ,
    data_cadastro timestamp not null ,
    data_atualizacao timestamp
);