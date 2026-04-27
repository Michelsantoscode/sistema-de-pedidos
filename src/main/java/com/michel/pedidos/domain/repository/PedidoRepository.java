package com.michel.pedidos.domain.repository;

import com.michel.pedidos.domain.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository <Pedido, Long> {

    void deleteByProdutoId(Long produtoId);

}
