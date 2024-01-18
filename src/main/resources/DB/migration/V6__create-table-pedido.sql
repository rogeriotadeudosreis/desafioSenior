create table pedido
(
    id             serial primary key,
    numero         varchar(20) not null,
    inicioVigencia timestamp   not null,
    fimVigencia    timestamp,
    cliente_id     int
);