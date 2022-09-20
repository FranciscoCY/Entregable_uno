package nttdata.grupouno.com.Clients.services.dto;

import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.models.dto.NaturalClients;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientsNaturalService {

    Mono<NaturalClients> findAllById(String id);
    Mono<NaturalClients> findByDocumentNumber(Long documentNumber);
    Flux<NaturalClients> findByNames(String names);
    Flux<NaturalClients> findByLastNames(String lastNames);
}
