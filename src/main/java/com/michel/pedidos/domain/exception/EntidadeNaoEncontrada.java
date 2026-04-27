package com.michel.pedidos.domain.exception;

public class EntidadeNaoEncontrada extends RuntimeException {
    public EntidadeNaoEncontrada(String message) {
        super(message);
    }
}
