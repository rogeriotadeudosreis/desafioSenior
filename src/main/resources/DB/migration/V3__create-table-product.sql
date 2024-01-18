create table product
(
    id              serial primary key,
    nome            varchar(200)   not null,
    codigo          varchar(255)   not null,
    preco           decimal(10, 2) not null,
    tipo_produto    varchar(10),
    inicio_vigencia timestamp      not null,
    fim_vigencia    timestamp,
    atualizacao     timestamp
);