package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.convert.ClientsConvert;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.models.dto.ClientsDto;
import nttdata.grupouno.com.Clients.repositories.ClientesRepository;
import nttdata.grupouno.com.Clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientsService {

    @Autowired
    private ClientesRepository clientesRepository;

    @Autowired
    private ClientsConvert clientsConvert;

    private final WebClient webClient;

    public ClientServiceImpl(WebClient.Builder webClientBuilder){
        this.webClient= webClientBuilder.baseUrl("http://localhost:8002").build();
    }

    @Override
    public Flux<ClientsDto> listAllClients(Long id) {
        return clientesRepository.findAll().flatMap(clients -> {
            ClientsDto dto=clientsConvert.convertDTO(clients);
            LegalPerson legalPerson1=new LegalPerson();
            List<LegalPerson> list=new ArrayList<>();

            Mono<LegalPerson> legalPersonMono= this.webClient.get().uri("/api/legalPerson/{id}",dto.getIdPerson()).retrieve().bodyToMono(LegalPerson.class);

            return legalPersonMono.flatMap(x->{
                legalPerson1.setId(x.getId());
                legalPerson1.setRuc(x.getRuc());
                legalPerson1.setBusinessName(x.getBusinessName());
                list.add(legalPerson1);
                dto.setLegalPersonList(list);
                return Mono.just(dto);
            });
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
