-- public.usuario definition
CREATE TABLE USUARIO
(
    id TEXT PRIMARY KEY UNIQUE NOT NULL,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

-- public.cliente definition
CREATE TABLE cliente
(
    id               int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    data_atualizacao timestamp NULL,
    data_fim         timestamp NULL,
    data_inicio      timestamp    NOT NULL,
    email            varchar(200) NOT NULL,
    nome             varchar(200) NOT NULL,
    CONSTRAINT cliente_pkey PRIMARY KEY (id)
);

-- public.produto definition
CREATE TABLE produto
(
    id               int8         NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    codigo           varchar(200) NOT NULL,
    data_atualizacao timestamp NULL,
    data_fim         timestamp NULL,
    data_inicio      timestamp    NOT NULL,
    nome             varchar(200) NOT NULL,
    preco            float8       NOT NULL,
    tipo_produto     varchar(10)  NOT NULL,
    CONSTRAINT produto_pkey PRIMARY KEY (id)
);
--
-- -- public.pedido definition
CREATE TABLE pedido
(
    id               int8        NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    data_fim         timestamp NULL,
    data_inicio      timestamp NULL,
    desconto         float8,
    numero_pedido    varchar(50) NULL,
    situacao         varchar(10) NOT NULL,
    subtotal_produto varchar(10) NOT NULL,
    total_pedido     float8 NULL,
    cliente_id       int8        NOT NULL,
    CONSTRAINT pedido_pkey PRIMARY KEY (id)
);
--
-- -- public.pedido foreign keys
--
ALTER TABLE pedido
    ADD CONSTRAINT fk_cliente FOREIGN KEY (cliente_id) REFERENCES cliente (id);
--
-- -- public.item_pedido definition
CREATE TABLE item
(
    id         int8   NOT NULL GENERATED BY DEFAULT AS IDENTITY,
    preco      float8 NOT NULL,
    quantidade int4 NULL,
    subtotal   float8 NULL,
    produto_id int8   NOT NULL,
    CONSTRAINT item_pedido_pkey PRIMARY KEY (id),
    CONSTRAINT item_pedido_quantidade_check CHECK ((quantidade >= 1))
);
--
-- -- public.item_pedido foreign keys
--
ALTER TABLE item
    ADD CONSTRAINT fk_produto FOREIGN KEY (produto_id) REFERENCES produto (id);

-- public.item_pedido definition
CREATE TABLE item_pedido
(
    pedido_id int8 not null,
    item_id int8 not null

);
--

-- ------------------------------------------------------------------------------------------------------------------------

-- INSERTS

-- Dando carga na tabela de clientes
INSERT INTO cliente
    (data_atualizacao, data_fim, data_inicio, email, nome)
values (null, null, '2022-12-12 01:01:09.977', 'cliente1@gmail.com', 'cliente 1'),
       (null, null, '2022-12-12 01:01:09.977', 'cliente2@gmail.com', 'cliente 2'),
       (null, null, '2022-12-12 01:01:09.977', 'cliente3@gmail.com', 'cliente 3'),
       (null, null, '2022-12-12 01:01:09.977', 'cliente4@gmail.com', 'cliente 4'),
       (null, null, '2022-12-12 01:01:09.977', 'cliente5@gmail.com', 'cliente 5'),
       (null, null, '2022-12-12 01:01:09.977', 'cliente6@gmail.com', 'cliente 6');

-- Dando carga na tela de produtos
INSERT INTO produto
(data_atualizacao, data_fim, data_inicio, nome, preco, codigo, tipo_produto)
VALUES (null, null, '2022-12-05 11:01:09.977', 'produto1', 1500.0, 'cod0010', 'PRODUTO'),
       (null, null, '2022-12-05 11:01:09.977', 'produto2', 150.0, 'cod0011', 'SERVICO'),
       (null, null, '2022-12-05 11:01:09.977', 'produto3', 3000.0, 'cod0012', 'PRODUTO'),
       (null, null, '2022-12-05 11:01:09.977', 'produto4', 150.0, 'cod0013', 'SERVICO'),
       (null, null, '2022-12-05 11:01:09.977', 'produto5', 1000.0, 'cod0014', 'PRODUTO'),
       (null, null, '2022-12-05 11:01:09.977', 'produto6', 150.0, 'cod0015', 'SERVICO');

-- Dando carga na tabela de pedidos

INSERT INTO pedido
(data_fim, data_inicio, desconto, numero_pedido, situacao, subtotal_produto, total_pedido, cliente_id)
VALUES (null, '2022-12-22 03:30:32.908', 500.0, null, 'FECHADO', 3000.0, 2500.0, 1),
       (null, '2022-12-13 06:01:09.977', 500.0, null, 'FECHADO', 3000.0, 2500.0, 2),
       (null, '2022-12-13 06:01:09.977', 500.0, null, 'FECHADO', 3000.0, 2500.0, 3);

-- Dando carga na tabela de itens do pedido

INSERT INTO item
    (preco, quantidade, subtotal, produto_id)
VALUES (1500.0, 1, 1500.0, 1),
       (150.0, 1, 150.0, 2),
       (3000.0, 1, 3000.0, 3),
       (150.0, 1, 150.0, 4),
       (1000.0, 1, 1000.0, 5),
       (150.0, 1, 150.0, 6);

INSERT INTO public.item_pedido
(pedido_id, item_id)
VALUES (1, 1),
       (1, 2),
       (2, 3),
       (2, 4),
       (3, 5),
       (3, 6);


