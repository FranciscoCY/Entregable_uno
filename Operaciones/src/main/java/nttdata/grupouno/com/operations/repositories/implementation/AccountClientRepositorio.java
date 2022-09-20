package nttdata.grupouno.com.operations.repositories.implementation;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import nttdata.grupouno.com.operations.models.AccountClientModel;

public interface AccountClientRepositorio extends ReactiveMongoRepository<AccountClientModel, String>{
    
}
