package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MovementDetailModel;
import nttdata.grupouno.com.operations.repositories.implementation.MovementDetailRepository;
import nttdata.grupouno.com.operations.services.IMovementDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MovementDetailService implements IMovementDetailService {

    @Autowired
    private MovementDetailRepository movementRepository;

    @Override
    public void createAccount(MovementDetailModel movement) {
        movementRepository.save(movement).subscribe();
    }

    @Override
    public Mono<MovementDetailModel> findById(Integer id) {
        return movementRepository.findById(id);
    }

    @Override
    public Flux<MovementDetailModel> findAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public Flux<MovementDetailModel> findByAccount(String account) {
        return movementRepository.findAll(Example.of(new MovementDetailModel(null,account,null,null, null,null,null)));
    }


}
