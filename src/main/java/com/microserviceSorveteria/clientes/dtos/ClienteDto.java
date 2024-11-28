package com.microserviceSorveteria.clientes.dtos;

import com.microserviceSorveteria.clientes.models.Cliente;

public record ClienteDto(String nome, String cpf) {
    public ClienteDto(Cliente cliente){
        this(cliente.getNome(), cliente.getCpf());
    }
}
