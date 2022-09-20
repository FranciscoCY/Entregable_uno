package nttdata.grupouno.com.Clients.services.implementation.dto;

import nttdata.grupouno.com.Clients.convert.ClientsConvert;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.models.dto.ClientsLegal;
import nttdata.grupouno.com.Clients.repositories.LegalPersonRepository;
import nttdata.grupouno.com.Clients.services.dto.ClientsLegalService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
@Component
public class ClientsLegalServiceImpl implements ClientsLegalService {

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Autowired
    private ClientsConvert clientsConvert;

    private final WebClient webClient;

    public ClientsLegalServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8002").build();
    }

    @Override
    public Flux<LegalPerson> listAllLegalPerson() {
        return null;
    }

    @Override
    public Mono<ClientsLegal> findAllById(String id) {
        return legalPersonRepository.findById(id).flatMap(legal -> {
            ClientsLegal dto = clientsConvert.convertDtoLegal(legal);
            Mono<Clients> clientsMono = this.webClient.get().uri("/api/clients/findByIdPerson/{idPerson}", dto.getIdPerson()).retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(x -> {
                dto.setId(x.getId());
                dto.setIdTypePerson(x.getIdTypePerson());
                dto.setIdPerson(x.getIdPerson());
                return Mono.just(dto);
            });
        });
    }

    @Override
    public Mono<LegalPerson> createLegalPerson(LegalPerson legalPerson) {
        return null;
    }

    @Override
    public Mono<LegalPerson> updateLegalPerson(LegalPerson legalPerson, String id) {
        return null;
    }

    @Override
    public Mono<Void> deleteLegalPerson(String id) {
        return null;
    }

    @Override
    public Mono<ClientsLegal> findByRuc(Long ruc) {
        return legalPersonRepository.findByRuc(ruc).flatMap(legal -> {
            ClientsLegal dto = clientsConvert.convertDtoLegal(legal);
            Mono<Clients> clientsMono = this.webClient.get().uri("/api/clients/findByIdPerson/{idPerson}", dto.getIdPerson()).retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(x -> {
                dto.setId(x.getId());
                dto.setIdTypePerson(x.getIdTypePerson());
                dto.setIdPerson(x.getIdPerson());
                return Mono.just(dto);
            });
        });
    }

    @Override
    public Flux<ClientsLegal> findByBusinessName(String businessName) {
        return legalPersonRepository.findByBusinessName(businessName).flatMap(legal -> {
            ClientsLegal dto = clientsConvert.convertDtoLegal(legal);
            Mono<Clients> clientsMono = this.webClient.get().uri("/api/clients/findByIdPerson/{idPerson}", dto.getIdPerson()).retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(x -> {
                dto.setId(x.getId());
                dto.setIdTypePerson(x.getIdTypePerson());
                dto.setIdPerson(x.getIdPerson());
                return Mono.just(dto);
            });
        });
    }
}
