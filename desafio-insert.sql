 INSERT INTO public.cliente
(data_fim, data_inicio, email, nome)
values
('null', '2022-12-12 01:01:09.977', 'cliente1@gmail.com', 'cliente 1'),
('null', '2022-12-12 01:01:09.977', 'cliente2@gmail.com', 'cliente 2'),
('null', '2022-12-12 01:01:09.977', 'cliente3@gmail.com', 'cliente 3'),
('null', '2022-12-12 01:01:09.977', 'cliente4@gmail.com', 'cliente 4'),
('null', '2022-12-12 01:01:09.977', 'cliente5@gmail.com', 'cliente 5'),
('null', '2022-12-12 01:01:09.977', 'cliente6@gmail.com', 'cliente 6');


INSERT INTO public.produto
(data_fim, data_inicio, nome, preco, codigo)
VALUES(null, '2022-12-12 11:01:09.977', 'produto1', 150.0, 'cod0010');
VALUES(null, '2022-12-12 11:01:09.977', 'produto2', 150.0, 'cod0011');
VALUES(null, '2022-12-12 11:01:09.977', 'produto3', 150.0, 'cod0012');
VALUES(null, '2022-12-12 11:01:09.977', 'produto4', 150.0, 'cod0013');
VALUES(null, '2022-12-12 11:01:09.977', 'produto5', 150.0, 'cod0014');
VALUES(null, '2022-12-12 11:01:09.977', 'produto6', 150.0, 'cod0015');
VALUES(null, '2022-12-12 11:01:09.977', 'produto7', 150.0, 'cod0016');
VALUES(null, '2022-12-12 11:01:09.977', 'produto8', 150.0, 'cod0017');
VALUES(null, '2022-12-12 11:01:09.977', 'produto9', 150.0, 'cod0018');
VALUES(null, '2022-12-12 11:01:09.977', 'produto10', 150.0, 'cod0019');
VALUES(null, '2022-12-12 11:01:09.977', 'produto11', 150.0, 'cod0020');
VALUES(null, '2022-12-12 11:01:09.977', 'produto12', 150.0, 'cod0021');

INSERT INTO public.item_pedido
(desconto, preco, quantidade, id_pedido, id_produto, subtotal)
VALUES(0, 200.0, 1, 1, 1, 0);
VALUES(0, 200.0, 2, 2, 2, 0);
VALUES(0, 200.0, 3, 3, 3, 0);
VALUES(0, 200.0, 4, 4, 4, 0);
VALUES(0, 200.0, 5, 5, 5, 0);
VALUES(0, 200.0, 6, 6, 6, 0);
VALUES(0, 200.0, 1, 7, 1, 0);
VALUES(0, 200.0, 2, 8, 2, 0);
VALUES(0, 200.0, 3, 9, 3, 0);
VALUES(0, 200.0, 4, 10, 4, 0);
VALUES(0, 200.0, 5, 11, 5, 0);
VALUES(0, 200.0, 6, 12, 6, 0);

INSERT INTO public.pedido
(data_fim, data_inicio, id_cliente, total)
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);
VALUES('', '2022-12-12 06:01:09.977', 0, 0);