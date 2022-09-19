package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.convert.ClientsConvert;
import nttdata.grupouno.com.Clients.convert.NaturalClientsConvert;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.models.dto.ClientsDto;
import nttdata.grupouno.com.Clients.models.dto.NaturalClients;
import nttdata.grupouno.com.Clients.repositories.LegalPersonRepository;
import nttdata.grupouno.com.Clients.services.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LegalPersonImpl implements LegalPersonService {

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private ClientsConvert clientsConvert;

    @Override
    public Flux<LegalPerson> listAllLegalPerson() {
        return legalPersonRepository.findAll().flatMap(legaPerson -> {
            return Mono.just(legaPerson);
        });
    }

    @Override
    public Mono<LegalPerson> findAllById(String id) {
        return legalPersonRepository.findById(id).flatMap(legalPerson -> {
            return Mono.just(legalPerson);
        });
    }

    @Override
    public Mono<ClientsDto> createLegalPerson(LegalPerson legalPerson) {
        if(legalPerson == null){
            return null;
        }else{
            legalPerson.setId(UUID.randomUUID().toString());
            Clients clients = new Clients();
            clients.setIdPerson(legalPerson.getId());
            clients.setIdTypePerson(2L);
            clients.setIdPerson(legalPerson.getId());
            return clientService.createClient(clients).flatMap(clients1 -> {
                ClientsDto dto=clientsConvert.convertDTO(clients1);
                LegalPerson legal=new LegalPerson();
                List<LegalPerson> list=new ArrayList<>();
                Mono<LegalPerson> legalPersonMono = legalPersonRepository.save(legalPerson);
                return legalPersonMono.flatMap(legalPerson1 -> {
                    legal.setId(legalPerson1.getId());
                    legal.setRuc(legalPerson1.getRuc());
                    legal.setBusinessName(legalPerson1.getBusinessName());
                    legal.setMail(legalPerson1.getMail());
                    list.add(legal);
                    dto.setLegalPersonList(list);
                    return Mono.just(dto);
                });
            });

        }
    }

    @Override
    public Mono<LegalPerson> updateLegalPerson(LegalPerson legalPerson, String id) {
       return findAllById(id).flatMap(l ->{
           l.setBusinessName(legalPerson.getBusinessName());
           return legalPersonRepository.save(l);
       });
    }

    @Override
    public Mono<Void> deleteLegalPerson(String id) {
        return findAllById(id).flatMap(l -> legalPersonRepository.deleteById(l.getId()));
    }

    @Override
    public Mono<LegalPerson> findByRuc(Long ruc) {
        return legalPersonRepository.findByRuc(ruc).flatMap(l ->{
            return Mono.just(l);
        });
    }

    @Override
    public Flux<LegalPerson> findByBusinessName(String businessName) {
        return legalPersonRepository.findByBusinessName(businessName).flatMap(l ->{
            return Mono.just(l);
        });
    }
}
