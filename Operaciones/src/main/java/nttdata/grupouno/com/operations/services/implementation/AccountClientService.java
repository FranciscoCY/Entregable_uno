package nttdata.grupouno.com.operations.services.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import nttdata.grupouno.com.operations.models.AccountClientModel;
import nttdata.grupouno.com.operations.repositories.implementation.AccountClientRepositorio;
import nttdata.grupouno.com.operations.services.IAccountClientService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountClientService implements IAccountClientService {
    @Autowired
    private AccountClientRepositorio accountClientRepositorio;

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
        return accountClientRepositorio.save(model);
    }

    @Override
    public Flux<AccountClientModel> findByClientTypeAccount(String codeCliente, String typeAccount) {
        return accountClientRepositorio.findAll(Example.of(new AccountClientModel(codeCliente, null, null, null, typeAccount)));
    }

    @Override
    public Flux<AccountClientModel> findAll() {
        return accountClientRepositorio.findAll();
    }
}
