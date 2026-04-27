CREATE TABLE pedido (
     id BIGINT NOT NULL AUTO_INCREMENT,
     produto VARCHAR(255) NOT NULL,
     quantidade INT NOT NULL,
     status VARCHAR(20) NOT NULL,
     usuario_id BIGINT,

     PRIMARY KEY (id)
);

ALTER TABLE pedido
ADD CONSTRAINT fk_pedido_usuario
FOREIGN KEY (usuario_id)
REFERENCES usuario(id);