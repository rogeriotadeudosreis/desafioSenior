alter table pedido
    add constraint fk_cliente_id foreign key (cliente_id) references cliente (id) ;