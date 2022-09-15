package nttdata.grupouno.com.Clients.services;

import nttdata.grupouno.com.Clients.models.Clients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientsService {
    Flux<Clients> listAllClients();
    Mono<Clients> findAllById(Long id);
    Mono<Clients> createClient(Clients clients);
    Mono<Clients> updateClient(Clients client,Long id);
    Mono<Void> deleteClient(Long id);
    Flux<Clients> findByIdTypePerson(Long idTypePerson);
}
