create table item
(
    id         serial primary key,
    quantidade int,
    preco      decimal(10, 2),
    produto_id int,
    subTotal   decimal(10, 2)
);

alter table item
    add constraint fk_produto_id foreign key (produto_id) references produto (id);