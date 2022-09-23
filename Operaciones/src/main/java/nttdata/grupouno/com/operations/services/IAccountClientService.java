package nttdata.grupouno.com.operations.services;

import nttdata.grupouno.com.operations.models.AccountClientModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IAccountClientService {
    Flux<AccountClientModel> findByCodeClient(String codeClient);
    Flux<AccountClientModel> findByNumBerAccount(String numberAccount);
    Mono<AccountClientModel> registerClient(AccountClientModel model);
    Flux<AccountClientModel> findByClientTypeAccount(String codeCliente, String typeAccount);
    Flux<AccountClientModel> findAll();
    Mono<Long> countByCodeClientAndTypeAccount(String codeCliente, String typeAccount);
    Mono<Long> countByCodeClientAndTypeAccountAndTypeClient(String codeCliente, String typeAccountm, String typeClient);
    Mono<AccountClientModel> findById(String id);
}
