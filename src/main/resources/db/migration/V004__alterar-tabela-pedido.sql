ALTER TABLE pedido DROP COLUMN produto;

ALTER TABLE pedido ADD produto_id BIGINT;

ALTER TABLE pedido ADD CONSTRAINT fk_pedido_produto
    FOREIGN KEY (produto_id) REFERENCES produto(id);