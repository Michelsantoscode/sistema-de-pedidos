CREATE TABLE produto (
      id BIGINT NOT NULL AUTO_INCREMENT,
      nome VARCHAR(60) NOT NULL,
      preco DECIMAL(10,2) NOT NULL,
      quantidade INT NOT NULL,
      PRIMARY KEY (id)
);