package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.repositories.implementation.MasterAccountRepository;
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

    @Override
    public Mono<MasterAccountModel> createAccount(MasterAccountModel account){
        account.setId(UUID.randomUUID().toString());
        return accountRepository.save(account);
    }
    public Mono<MasterAccountModel> findByAccount(String id){
        return accountRepository.findById(id);
    }
    @Override
    public Flux<MasterAccountModel> findAllAccount(){
        return  accountRepository.findAll();
    }
    @Override
    public Mono<MasterAccountModel> updateAccount(MasterAccountModel account){
        return accountRepository.save(account);
    }
    @Override
    public Mono<Void> deleteBydId(String id){
        return accountRepository.deleteById(id);
    }

    @Override
    public Flux<MasterAccountModel> findStartDate(String date) {
        return accountRepository.findByStartDate(date);
    }
}
