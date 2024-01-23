alter table item
    add column pedido_id int;
alter table item
    add constraint fk_pedido_id foreign key (pedido_id) references pedido (id);