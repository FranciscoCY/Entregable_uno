package nttdata.grupouno.com.operations.services;

import nttdata.grupouno.com.operations.models.MovementDetail;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMovementDetailService {

    void createAccount(MovementDetail movement);
    Mono<MovementDetail> findById(Integer id);
    Flux<MovementDetail> findAllMovements();

    Flux<MovementDetail> findByAccount(String account);

}
