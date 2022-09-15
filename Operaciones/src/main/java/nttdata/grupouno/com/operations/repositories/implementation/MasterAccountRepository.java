package nttdata.grupouno.com.operations.repositories.implementation;

import nttdata.grupouno.com.operations.models.MasterAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MasterAccountRepository extends ReactiveMongoRepository<MasterAccount, Integer> {
}
