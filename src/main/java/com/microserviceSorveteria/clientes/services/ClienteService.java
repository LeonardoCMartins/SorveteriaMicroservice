package com.microserviceSorveteria.clientes.services;

import com.microserviceSorveteria.clientes.dtos.ClienteDto;
import com.microserviceSorveteria.clientes.models.Cliente;
import com.microserviceSorveteria.clientes.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public ClienteDto save(ClienteDto clienteDto){
        Cliente cliente = new Cliente(clienteDto.nome(), clienteDto.cpf());
        repository.save(cliente);
        return new ClienteDto(cliente);
    }

    public List<ClienteDto> getAll(){
        List<Cliente> clientes = repository.findAll();
        return clientes.stream().map(ClienteDto::new).toList();
    }

    public ClienteDto getById(Long id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("não achou"));
        return new ClienteDto(cliente);
    }

    public ClienteDto update(Long id, ClienteDto clienteDto){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("não achou"));
        cliente.setNome(clienteDto.nome());
        cliente.setCpf(clienteDto.cpf());

        Cliente updateCliente = repository.save(cliente);
        return new ClienteDto(updateCliente);
    }

    public void delete(Long id){
        Cliente cliente = repository.findById(id).orElseThrow(() -> new RuntimeException("não achou"));
        repository.delete(cliente);
    }
}
