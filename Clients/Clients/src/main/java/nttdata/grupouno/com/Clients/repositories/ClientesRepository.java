package nttdata.grupouno.com.Clients.repositories;

import nttdata.grupouno.com.Clients.models.Clients;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ClientesRepository extends ReactiveMongoRepository<Clients,Long> {
}
