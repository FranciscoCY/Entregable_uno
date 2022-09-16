package nttdata.grupouno.com.operations.services;

import nttdata.grupouno.com.operations.models.MasterAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMasterAccountServices {
    void createAccount(MasterAccount account);
    Mono<MasterAccount> findByAccount(String id);
    Flux<MasterAccount> findAllAccount();
    Mono<MasterAccount> updateAccount(MasterAccount account);
    Mono<Void> deleteBydId(String id);
    Flux<MasterAccount> findStartDate(String date);
}
