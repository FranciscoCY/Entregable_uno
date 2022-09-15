package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.repositories.ClientesRepository;
import nttdata.grupouno.com.Clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientServiceImpl implements ClientsService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Override
    public Flux<Clients> listAllClients() {
        return clientesRepository.findAll().flatMap(clients -> {
            return Mono.just(clients);
        });
    }

    @Override
    public Mono<Clients> findAllById(Long id) {
        return clientesRepository.findById(id).flatMap(clients -> {
            return Mono.just(clients);
        });
    }

    @Override
    public Mono<Clients> createClient(Clients clients) {
        if(clients == null){
            return null;
        }else{
            return clientesRepository.save(clients);
        }
    }

    @Override
    public Mono<Clients> updateClient(Clients client, Long id) {
        return null;
    }

    @Override
    public Mono<Void> deleteClient(Long id) {
        return null;
    }

    @Override
    public Flux<Clients> findByIdTypePerson(Long idTypePerson) {
        return null;
    }
}
