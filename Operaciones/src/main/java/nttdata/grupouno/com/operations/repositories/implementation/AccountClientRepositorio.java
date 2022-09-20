package nttdata.grupouno.com.operations.repositories.implementation;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import nttdata.grupouno.com.operations.models.AccountClientModel;
import reactor.core.publisher.Flux;

public interface AccountClientRepositorio extends ReactiveMongoRepository<AccountClientModel, String>{
    Flux<AccountClientModel> findByCodeClient(String codeClient);
    Flux<AccountClientModel> findByNumberAccount(String numberAccount);
}
