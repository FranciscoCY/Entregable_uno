package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.models.TypeModel;
import nttdata.grupouno.com.operations.repositories.implementation.MasterAccountRepository;
import nttdata.grupouno.com.operations.repositories.implementation.TypeAccountRepository;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class MasterAccountServices implements IMasterAccountServices {
    @Autowired
    private MasterAccountRepository accountRepository;
    @Autowired
    private TypeAccountRepository typeAccountRepository;

    @Override
    public Mono<MasterAccountModel> createAccount(MasterAccountModel account) {
        account.setId(UUID.randomUUID().toString());
        account.setType(new TypeModel(account.getType().getCode(), null, null, null, null));
        
        return accountRepository.save(account).flatMap(c -> {
            c.setType(typeAccountRepository.findById(c.getType().getCode()).block());
            return Mono.just(c);
        });
    }

    @Override
    public Mono<MasterAccountModel> findByAccount(String id) {
        return accountRepository.findById(id).flatMap(c -> {
            c.setType(typeAccountRepository.findById(c.getType().getCode()).block());
            return Mono.just(c);
        });
    }

    @Override
    public Flux<MasterAccountModel> findAllAccount() {
        return accountRepository.findAll().flatMap(c -> {
            c.setType(typeAccountRepository.findById(c.getType().getCode()).block());
            return Flux.just(c);
        });
    }

    @Override
    public Mono<MasterAccountModel> updateAccount(MasterAccountModel account, String id) {
        return accountRepository.findById(id).flatMap(c -> accountRepository.save(account));
    }

    @Override
    public Mono<Void> deleteBydId(String id) {
        return accountRepository.deleteById(id);
    }

    @Override
    public Flux<MasterAccountModel> findStartDate(String date) {
        return accountRepository.findByStartDate(date);
    }
}
