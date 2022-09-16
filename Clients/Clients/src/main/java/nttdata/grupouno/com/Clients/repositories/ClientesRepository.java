package nttdata.grupouno.com.Clients.repositories;

import nttdata.grupouno.com.Clients.models.Clients;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface ClientesRepository extends ReactiveMongoRepository<Clients,Long> {

    Flux<Clients> findByIdTypePerson(Long idTypePerson);
}
