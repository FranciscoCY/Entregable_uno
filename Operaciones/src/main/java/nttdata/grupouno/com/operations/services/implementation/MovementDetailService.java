package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MovementDetail;
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
    public void createAccount(MovementDetail movement) {
        movementRepository.save(movement).subscribe();
    }

    @Override
    public Mono<MovementDetail> findById(Integer id) {
        return movementRepository.findById(id);
    }

    @Override
    public Flux<MovementDetail> findAllMovements() {
        return movementRepository.findAll();
    }

    @Override
    public Flux<MovementDetail> findByAccount(String account) {
        return movementRepository.findAll(Example.of(new MovementDetail(null,account,null,null, null,null,null)));
    }


}
