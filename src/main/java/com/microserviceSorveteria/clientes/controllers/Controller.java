package com.microserviceSorveteria.clientes.controllers;

import com.microserviceSorveteria.clientes.dtos.ClienteDto;
import com.microserviceSorveteria.clientes.services.ClienteService;
import com.microserviceSorveteria.clientes.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.microserviceSorveteria.clientes.models.MenuItem;

import java.awt.*;
import java.util.List;


@RestController
@RequestMapping("/clientes")
public class Controller {

    @Autowired
    private ClienteService service;
    @Autowired
    private MenuService menuService;

    @GetMapping("/menu")
    public List<MenuItem> getMenu() {
        return (List<MenuItem>) menuService.fetchMenu();
    }

    @PostMapping("/menu")
    public ResponseEntity<String> addMenuItem(@RequestBody MenuItem newItem) {
        menuService.sendPostToMenu(newItem);
        return ResponseEntity.ok("Item enviado com sucesso!");
    }

    @PostMapping("/post")
    public ResponseEntity save(@RequestBody ClienteDto clienteDto){
        service.save(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente criado");
    }

    @GetMapping("/get")
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(service.getById(id));
    }

    @PostMapping("/put/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody ClienteDto clienteDto){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, clienteDto));
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado");
    }


}
