package com.github.andredias20;

import com.github.andredias20.controller.ProdutoDAO;
import com.github.andredias20.model.Produto;

public class Main {
    public static void main(String[] args) {
        ProdutoDAO dao = ProdutoDAO.getInstance();

        Produto p1 = new Produto();
        p1.setNome("Notebook");
        p1.setDescricao("Notebook Dell Inspiron");
        p1.setPreco(3000.00);

        Produto p2 = new Produto();
        p2.setId(1);
        p2.setNome("Notebook");
        p2.setDescricao("Notebook Acer Nitro");
        p2.setPreco(3500.00);

        dao.persist(p1);

        dao.merge(p2);

        dao.findAll().forEach(System.out::println);

        Produto finded = dao.findById(1);
        System.out.println(finded);



    }
}