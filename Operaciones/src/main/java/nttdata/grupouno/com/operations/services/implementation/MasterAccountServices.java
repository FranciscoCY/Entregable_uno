package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MasterAccount;
import nttdata.grupouno.com.operations.repositories.implementation.MasterAccountRepository;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class MasterAccountServices implements IMasterAccountServices {
    @Autowired
    private MasterAccountRepository accountRepository;

    @Override
    public void createAccount(MasterAccount account){
        account.setId(UUID.randomUUID().toString());
        accountRepository.save(account).subscribe();
    }
    public Mono<MasterAccount> findByAccount(String id){
        return accountRepository.findById(id);
    }
    @Override
    public Flux<MasterAccount> findAllAccount(){
        return  accountRepository.findAll();
    }
    @Override
    public Mono<MasterAccount> updateAccount(MasterAccount account){
        return accountRepository.save(account);
    }
    @Override
    public Mono<Void> deleteBydId(String id){
        return accountRepository.deleteById(id);
    }

    @Override
    public Flux<MasterAccount> findStartDate(String date) {
        return accountRepository.findAll(Example.of(new MasterAccount(null, null, null, date, null, null, null, null)));
    }
}
