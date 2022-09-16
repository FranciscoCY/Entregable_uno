package nttdata.grupouno.com.Clients.repositories;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface LegalPersonRepository extends ReactiveMongoRepository<LegalPerson,Long> {

    Flux<LegalPerson> findByRuc(Long ruc);
    Flux<LegalPerson> findByBusinessName(String businessName);
}
