package nttdata.grupouno.com.operations.repositories;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IMovementDetailRepository extends ReactiveMongoRepository<MasterAccountModel, Integer> {}