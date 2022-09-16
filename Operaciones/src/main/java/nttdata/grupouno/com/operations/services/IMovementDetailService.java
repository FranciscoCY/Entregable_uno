package nttdata.grupouno.com.operations.services;

import nttdata.grupouno.com.operations.models.MovementDetailModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovementDetailService {

    void createAccount(MovementDetailModel movement);
    Mono<MovementDetailModel> findById(Integer id);
    Flux<MovementDetailModel> findAllMovements();

    Flux<MovementDetailModel> findByAccount(String account);

}
