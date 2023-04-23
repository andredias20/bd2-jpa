package com.github.andredias20.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "produto")
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    private Double preco;
    private String descricao;
}
