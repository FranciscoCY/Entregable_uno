package nttdata.grupouno.com.Clients.services.implementation;

import lombok.RequiredArgsConstructor;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.dto.ClientsNatural;
import nttdata.grupouno.com.Clients.repositories.ClientesRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class ClientServiceImplTest {
    @Mock
    private ClientesRepository clientesRepository;
    @InjectMocks
    private ClientServiceImpl clientService;
    @Autowired
    private Clients clients;
    @Autowired
    private Mono<Clients> clientsMono;
    @Autowired
    private Flux<Clients> clientsFlux;

    @BeforeEach
    void init() {
        clients = new Clients("0327ebad-f4ef-4fe8-8d9f-6edc0f2328b5",1L,"ae452d5c-1cc1-4ecb-8108-22294844a7a5");
        clientsMono = Mono.just(clients);
        clientsFlux = clientsMono.flux();
    }

    @Test
    void listAllClientsLegal() {
        Mockito.when(clientesRepository.findAll()).thenReturn(clientsFlux);
        Flux<ClientsNatural> response = clientService.listAllClientsNatural();
    }

    @Test
    void listAllClientsNatural() {
    }

    @Test
    void findAllById() {
    }

    @Test
    void createClient() {
        Mockito.when(clientesRepository.save(clients)).thenReturn(clientsMono);
        Mono<Clients> response = clientService.createClient(clients);
        assertEquals(response,clientsMono);
    }

    @Test
    void updateClient() {
    }

    @Test
    void deleteClient() {
    }

    @Test
    void findByIdTypePerson() {
    }

    @Test
    void findByIdPerson() {
    }
}