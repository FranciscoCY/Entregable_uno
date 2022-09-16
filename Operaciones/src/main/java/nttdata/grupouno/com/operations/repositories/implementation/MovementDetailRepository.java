package nttdata.grupouno.com.operations.repositories.implementation;

import nttdata.grupouno.com.operations.models.MovementDetail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovementDetailRepository extends ReactiveMongoRepository<MovementDetail, Integer> {
}
