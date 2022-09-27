package nttdata.grupouno.com.operations.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import nttdata.grupouno.com.operations.models.AccountClientModel;
import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.repositories.implementation.AccountClientRepositorio;
import nttdata.grupouno.com.operations.services.IAccountClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountClientService implements IAccountClientService {
    @Autowired
    private AccountClientRepositorio accountClientRepositorio;

    private final WebClient webClient;

    public AccountClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8001").build();
    }

    @Override
    public Flux<AccountClientModel> findByCodeClient(String codeClient) {
        return accountClientRepositorio.findByCodeClient(codeClient);
    }

    @Override
    public Flux<AccountClientModel> findByNumBerAccount(String numberAccount) {
        return accountClientRepositorio.findByNumberAccount(numberAccount);
    }

    @Override
    public Mono<AccountClientModel> registerClient(AccountClientModel model) {
        Mono<MasterAccountModel> validClient = this.webClient.get().uri("/api/clients/{id}", model.getCodeClient()).retrieve().bodyToMono(MasterAccountModel.class);
        return validClient.flatMap(x -> {
            if(!x.getId().equals(model.getCodeClient()))
                return Mono.empty();
            return accountClientRepositorio.save(model);
        }).onErrorResume(y -> Mono.empty());
    }

    @Override
    public Flux<AccountClientModel> findByClientTypeAccount(String codeCliente, String typeAccount) {
        return accountClientRepositorio.findByNumberAccountAndTypeAccount(codeCliente, typeAccount);
    }

    @Override
    public Flux<AccountClientModel> findAll() {
        return accountClientRepositorio.findAll();
    }

    @Override
    public Mono<Long> countByCodeClientAndTypeAccount(String codeClient, String typeAccount) {
        return accountClientRepositorio.countByCodeClientAndTypeAccount(codeClient, typeAccount);
    }

    @Override
    public Mono<AccountClientModel> findById(String id){
        return accountClientRepositorio.findById(id);
    }

    @Override
    public Mono<Long> countByCodeClientAndTypeAccountAndTypeClient(String codeCliente, String typeAccount, String typeClient) {
        return accountClientRepositorio.countByCodeClientAndTypeAccountAndTypeClient(codeCliente, typeAccount, typeClient);
    }

    @Override
    public Mono<Long> countByCodeClientAndTypeAccountLike(String codeClient, String typeAccount) {
        if(typeAccount == null || typeAccount.isBlank())
            return Mono.just(Long.valueOf("1"));
        return accountClientRepositorio.countByCodeClientAndTypeAccountLike(codeClient, typeAccount);
    }
}
