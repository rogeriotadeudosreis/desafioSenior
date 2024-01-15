create table product (
    id serial primary key,
    nome varchar(200) not null,
    codigo varchar(255) not null,
    preco decimal(10,2) not null,
    tipo_produto varchar(10),
    data_inicio timestamp not null,
    data_fim timestamp,
    data_atualizacao timestamp
);