package com.microserviceSorveteria.clientes.services;

import com.microserviceSorveteria.clientes.models.MenuItem;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class MenuService {

    private final WebClient webClient;

    public MenuService(WebClient.Builder webClientBuilder) {
        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 25000)
                .responseTimeout(Duration.ofSeconds(25))
                .doOnConnected(conn ->
                        conn.addHandlerLast(new ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                                .addHandlerLast(new WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                );

        this.webClient = webClientBuilder
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://menu-yqkj.onrender.com")
                .build();
    }

    public List<MenuItem> getMenu() {
        MenuItem[] menuItems = webClient.get()
                .uri("/menu")
                .retrieve()
                .bodyToMono(MenuItem[].class)
                .block();

        return Arrays.asList(menuItems);
    }

    public void postMenu(MenuItem newItem) {
        webClient.post()
                .uri("/menu")
                .bodyValue(newItem)
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}


