package com.microserviceSorveteria.clientes.services;

import com.microserviceSorveteria.clientes.models.MenuItem;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

@Service
public class MenuService {
    private final RestTemplate restTemplate = new RestTemplate();

    public List<MenuItem> fetchMenu() {
        String url = "https://menu-yqkj.onrender.com/menu";
        MenuItem[] menuItems = restTemplate.getForObject(url, MenuItem[].class);
        return Arrays.asList(menuItems);
    }
}



