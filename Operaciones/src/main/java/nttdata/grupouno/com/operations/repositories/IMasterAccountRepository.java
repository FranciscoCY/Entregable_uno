package nttdata.grupouno.com.operations.repositories;

import nttdata.grupouno.com.operations.models.MasterAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IMasterAccountRepository extends ReactiveMongoRepository<MasterAccount, Integer> {
}
