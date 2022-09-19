package nttdata.grupouno.com.Clients.services;

import nttdata.grupouno.com.Clients.models.Clients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientsService {
    Flux<Clients> listAllClients();
    Mono<Clients> findAllById(String id);
    Mono<Clients> createClient(Clients clients);
    Mono<Clients> updateClient(Clients client,String id);
    Mono<Void> deleteClient(String id);
    Flux<Clients> findByIdTypePerson(Long idTypePerson);
}
