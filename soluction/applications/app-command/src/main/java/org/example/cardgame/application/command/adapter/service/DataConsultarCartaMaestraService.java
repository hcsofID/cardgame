package org.example.cardgame.application.command.adapter.service;

import org.example.cardgame.usecase.gateway.ListaDeCartaService;
import org.example.cardgame.usecase.gateway.model.CartaMaestra;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class DataConsultarCartaMaestraService implements ListaDeCartaService {
    private final ReactiveMongoTemplate template;

    public DataConsultarCartaMaestraService(ReactiveMongoTemplate template) {
        this.template = template;
    }


    @Override
    public Flux<CartaMaestra> obtenerCartasPepsico() {
        return template.findAll(CartaMaestra.class, "cards");
    }
}
