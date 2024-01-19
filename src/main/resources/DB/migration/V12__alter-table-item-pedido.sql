alter table item_pedido add constraint fk_item_id foreign key (item_id) references item(id) ;
alter table item_pedido add constraint fk_pedido_id foreign key (pedido_id) references pedido(id) ;