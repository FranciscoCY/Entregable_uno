package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.repositories.ClientesRepository;
import nttdata.grupouno.com.Clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

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
    public Mono<Clients> findAllById(String id) {
        return clientesRepository.findById(id).flatMap(clients -> {
            return Mono.just(clients);
        });
    }

    @Override
    public Mono<Clients> createClient(Clients clients) {
        if(clients == null){
            return null;
        }else{
            clients.setId(UUID.randomUUID().toString());
            return clientesRepository.save(clients);
        }
    }

    @Override
    public Mono<Clients> updateClient(Clients client, String id) {
        return  findAllById(id).flatMap(c ->{
            c.setMail(client.getMail());
            return clientesRepository.save(c);
        });
    }

    @Override
    public Mono<Void> deleteClient(String id) {
        return findAllById(id).flatMap(c -> clientesRepository.deleteById(c.getId()));
    }

    @Override
    public Flux<Clients> findByIdTypePerson(Long idTypePerson) {

        return clientesRepository.findByIdTypePerson(idTypePerson).flatMap(clients -> {
            return Mono.just(clients);
        });
    }
}
