package nttdata.grupouno.com.operations.repositories.implementation;

import nttdata.grupouno.com.operations.models.MovementDetailModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDetailRepository extends ReactiveMongoRepository<MovementDetailModel, Integer> {
}
