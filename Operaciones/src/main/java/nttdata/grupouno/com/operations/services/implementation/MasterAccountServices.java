package nttdata.grupouno.com.operations.services.implementation;

import nttdata.grupouno.com.operations.models.MasterAccount;
import nttdata.grupouno.com.operations.repositories.implementation.MasterAccountRepository;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MasterAccountServices implements IMasterAccountServices {
    @Autowired
    private MasterAccountRepository accountRepository;

    @Override
    public void createAccount(MasterAccount account){
        accountRepository.save(account).subscribe();
    }
    public Mono<MasterAccount> findByAccount(Integer id){
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
    public Mono<Void> deleteBydId(Integer id){
        return accountRepository.deleteById(id);
    }
}
