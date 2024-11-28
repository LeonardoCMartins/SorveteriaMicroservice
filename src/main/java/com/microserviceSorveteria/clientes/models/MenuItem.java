package com.microserviceSorveteria.clientes.models;

import lombok.Data;

@Data
public class MenuItem {
    private Long id;
    private String nome;
    private Double preco;
}
