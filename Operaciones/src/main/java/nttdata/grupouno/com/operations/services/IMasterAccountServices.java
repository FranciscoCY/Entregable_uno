package nttdata.grupouno.com.operations.services;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IMasterAccountServices {
    void createAccount(MasterAccountModel account);
    Mono<MasterAccountModel> findByAccount(String id);
    Flux<MasterAccountModel> findAllAccount();
    Mono<MasterAccountModel> updateAccount(MasterAccountModel account);
    Mono<Void> deleteBydId(String id);
    Flux<MasterAccountModel> findStartDate(String date);
}