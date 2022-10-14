package org.example.cardgame.application.queries.handle.materialize.gameview;

import co.com.sofka.domain.generic.DomainEvent;
import org.example.cardgame.application.queries.generic.ModelViewRepository;
import org.example.cardgame.application.queries.handle.materialize.MaterializeService;
import org.example.cardgame.domain.events.RondaTerminada;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Component
public class HandleRondaTerminada  implements MaterializeService {
    private final ModelViewRepository repository;

    public HandleRondaTerminada(ModelViewRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Void> doProcessing(DomainEvent input) {
        var event = (RondaTerminada)input;
        Map<String, Object> data = new HashMap<>();
        Map<String, Object> query = new HashMap<>();
        data.put("fecha", Instant.now());
        data.put("tiempo", 0);
        data.put("ronda.estaIniciada", false);
        data.put("tablero.cartas", new HashMap<>());
        data.put("tablero.habilitado", false);
        data.put("ronda.jugadores", event.getJugadorIds());

        query.put("_id", event.aggregateRootId());

        return repository.update(query, data, COLLECTION_GAME_VIEW);

    }
}