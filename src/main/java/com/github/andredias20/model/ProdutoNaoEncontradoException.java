package com.github.andredias20.model;

public class ProdutoNaoEncontradoException extends RuntimeException{
    public ProdutoNaoEncontradoException(String msg){
        super(msg);
    }
}
