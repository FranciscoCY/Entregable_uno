package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.repositories.LegalPersonRepository;
import nttdata.grupouno.com.Clients.services.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class LegalPersonImpl implements LegalPersonService {

    @Autowired
    private LegalPersonRepository legalPersonRepository;

    @Override
    public Flux<LegalPerson> listAllLegalPerson() {
        return legalPersonRepository.findAll().flatMap(legaPerson -> {
            return Mono.just(legaPerson);
        });
    }

    @Override
    public Mono<LegalPerson> findAllById(Long id) {
        return legalPersonRepository.findById(id).flatMap(legalPerson -> {
            return Mono.just(legalPerson);
        });
    }

    @Override
    public Mono<LegalPerson> createLegalPerson(LegalPerson legalPerson) {
        if(legalPerson == null){
            return null;
        }else{
            return legalPersonRepository.save(legalPerson);
        }
    }

    @Override
    public Mono<LegalPerson> updateLegalPerson(LegalPerson legalPerson, Long id) {
       return findAllById(id).flatMap(l ->{
           l.setBusinessName(legalPerson.getBusinessName());
           return legalPersonRepository.save(l);
       });
    }

    @Override
    public Mono<Void> deleteLegalPerson(Long id) {
        return findAllById(id).flatMap(l -> legalPersonRepository.deleteById(l.getId()));
    }

    @Override
    public Flux<LegalPerson> findByRuc(Long ruc) {
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
