package nttdata.grupouno.com.Clients.services;

import nttdata.grupouno.com.Clients.models.LegalPerson;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LegalPersonService {

    Flux<LegalPerson> listAllLegalPerson();
    Mono<LegalPerson> findAllById(Long id);
    Mono<LegalPerson> createLegalPerson(LegalPerson legalPerson);
    Mono<LegalPerson> updateLegalPerson(LegalPerson legalPerson,Long id);
    Mono<Void> deleteLegalPerson(Long id);
    Mono<LegalPerson> findByRuc(Long ruc);
    Flux<LegalPerson> findByBusinessName(String businessName);
}
