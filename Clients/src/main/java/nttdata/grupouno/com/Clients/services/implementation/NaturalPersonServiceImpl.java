package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.convert.ClientsConvert;
import nttdata.grupouno.com.Clients.convert.NaturalClientsConvert;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.models.dto.NaturalClients;
import nttdata.grupouno.com.Clients.repositories.NaturalPersonRepository;
import nttdata.grupouno.com.Clients.services.NaturalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class NaturalPersonServiceImpl implements NaturalPersonService {

    @Autowired
    NaturalPersonRepository naturalPersonRepository;

    @Autowired
    ClientServiceImpl clientService;

    @Autowired
    private ClientsConvert clientsConvert;

    @Autowired
    private NaturalClientsConvert naturalClientsConvert;

    @Override
    public Flux<NaturalPerson> listAllNaturalPerson() {
        return naturalPersonRepository.findAll();
    }

    @Override
    public Mono<NaturalPerson> findAllById(String id) {
        return naturalPersonRepository.findById(id);
    }

    @Override
    public Mono<NaturalClients> createNaturalPerson(NaturalPerson naturalPerson) {
        if(naturalPerson == null){
            return null;
        }else{
            naturalPerson.setId(UUID.randomUUID().toString());
            Clients clients = new Clients();
            clients.setIdPerson(naturalPerson.getId());
            clients.setIdTypePerson(1L);
            clients.setIdPerson(naturalPerson.getId());
            return clientService.createClient(clients).flatMap(clients1 -> {
                NaturalClients natural = naturalClientsConvert.convertNaturalClient(clients1);
                NaturalPerson person = new NaturalPerson();
                Mono<NaturalPerson> naturalPersonMono = naturalPersonRepository.save(naturalPerson);
                return naturalPersonMono.flatMap(naturalPerson1 -> {
                    person.setId(naturalPerson1.getId());
                    person.setDocumentNumber(naturalPerson1.getDocumentNumber());
                    person.setDocumentType(naturalPerson1.getDocumentType());
                    person.setNames(naturalPerson1.getNames());
                    person.setLastNames(naturalPerson1.getLastNames());
                    person.setGender(naturalPerson1.getGender());
                    person.setMail(naturalPerson1.getMail());

                    natural.setPerson(person);
                    return Mono.just(natural);
                });
            });
        }
    }

    @Override
    public Mono<NaturalPerson> updateNaturalPerson(NaturalPerson naturalPerson) {
        if(naturalPerson == null){
            return null;
        }else{
                return naturalPersonRepository.save(naturalPerson);
        }
    }

    @Override
    public Mono<Void> deleteNaturalPerson(String id) {
        return naturalPersonRepository.deleteById(id);
    }

    @Override
    public Mono<NaturalPerson> findByDocumentNumber(Long documentNumber) {
        return naturalPersonRepository.findByDocumentNumber(documentNumber);
    }

    @Override
    public Flux<NaturalPerson> findByNames(String names) {
        return naturalPersonRepository.findByNames(names);
    }

    @Override
    public Flux<NaturalPerson> findByLastNames(String lastNames) {
        return naturalPersonRepository.findByLastNames(lastNames);
    }
}
