CREATE TABLE pedido (
                        id BIGINT NOT NULL AUTO_INCREMENT,
                        status VARCHAR(50),
                        quantidade INT,
                        usuario_id BIGINT NOT NULL,
                        produto_id BIGINT NOT NULL,
                        PRIMARY KEY (id),
                        CONSTRAINT fk_pedido_usuario FOREIGN KEY (usuario_id) REFERENCES usuario(id),
                        CONSTRAINT fk_pedido_produto FOREIGN KEY (produto_id) REFERENCES produto(id)
);