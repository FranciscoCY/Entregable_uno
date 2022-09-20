package nttdata.grupouno.com.Clients.services.implementation.dto;

import nttdata.grupouno.com.Clients.convert.ClientsConvert;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.models.dto.NaturalClients;
import nttdata.grupouno.com.Clients.repositories.NaturalPersonRepository;
import nttdata.grupouno.com.Clients.services.dto.ClientsNaturalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClientsNaturalServiceImpl implements ClientsNaturalService {

    @Autowired
    private NaturalPersonRepository naturalPersonRepository;

    @Autowired
    private ClientsConvert clientsConvert;

    private final WebClient webClient;

    public ClientsNaturalServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8002").build();
    }

    @Override
    public Mono<NaturalClients> findAllById(String id) {
        return naturalPersonRepository.findById(id).flatMap(naturalPerson -> {
            NaturalClients naturalClients = clientsConvert.convertNaturalClient(naturalPerson);

            Mono<Clients> clientsMono = this.webClient.get()
                    .uri("/api/clients/findByIdPerson/{idPerson}", naturalClients.getIdPerson())
                    .retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(clients -> {
                naturalClients.setId(clients.getId());
                naturalClients.setIdTypePerson(clients.getIdTypePerson());
                naturalClients.setIdPerson(clients.getIdPerson());
                return Mono.just(naturalClients);
            });
        });
    }

    @Override
    public Mono<NaturalClients> findByDocumentNumber(Long documentNumber) {
        return naturalPersonRepository.findByDocumentNumber(documentNumber).flatMap(naturalPerson -> {
            NaturalClients naturalClients = clientsConvert.convertNaturalClient(naturalPerson);

            Mono<Clients> clientsMono = this.webClient.get()
                    .uri("/api/clients/findByIdPerson/{idPerson}", naturalClients.getIdPerson())
                    .retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(clients -> {
                naturalClients.setId(clients.getId());
                naturalClients.setIdTypePerson(clients.getIdTypePerson());
                naturalClients.setIdPerson(clients.getIdPerson());
                return Mono.just(naturalClients);
            });
        });
    }

    @Override
    public Flux<NaturalClients> findByNames(String names) {
        return naturalPersonRepository.findByNames(names).flatMap(naturalPerson -> {
            NaturalClients naturalClients = clientsConvert.convertNaturalClient(naturalPerson);

            Mono<Clients> clientsMono = this.webClient.get()
                    .uri("/api/clients/findByIdPerson/{idPerson}", naturalClients.getIdPerson())
                    .retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(clients -> {
                naturalClients.setId(clients.getId());
                naturalClients.setIdTypePerson(clients.getIdTypePerson());
                naturalClients.setIdPerson(clients.getIdPerson());
                return Mono.just(naturalClients);
            });
        });
    }

    @Override
    public Flux<NaturalClients> findByLastNames(String lastNames) {
        return naturalPersonRepository.findByLastNames(lastNames).flatMap(naturalPerson -> {
            NaturalClients naturalClients = clientsConvert.convertNaturalClient(naturalPerson);

            Mono<Clients> clientsMono = this.webClient.get()
                    .uri("/api/clients/findByIdPerson/{idPerson}", naturalClients.getIdPerson())
                    .retrieve().bodyToMono(Clients.class);

            return clientsMono.flatMap(clients -> {
                naturalClients.setId(clients.getId());
                naturalClients.setIdTypePerson(clients.getIdTypePerson());
                naturalClients.setIdPerson(clients.getIdPerson());
                return Mono.just(naturalClients);
            });
        });
    }
}
