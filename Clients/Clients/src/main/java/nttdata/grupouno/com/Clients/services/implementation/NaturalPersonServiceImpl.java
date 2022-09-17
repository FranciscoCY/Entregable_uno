package nttdata.grupouno.com.Clients.services.implementation;

import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.repositories.NaturalPersonRepository;
import nttdata.grupouno.com.Clients.services.NaturalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class NaturalPersonServiceImpl implements NaturalPersonService {

    @Autowired
    NaturalPersonRepository naturalPersonRepository;

    @Override
    public Flux<NaturalPerson> listAllNaturalPerson() {
        return naturalPersonRepository.findAll();
    }

    @Override
    public Mono<NaturalPerson> findAllById(Long id) {
        return naturalPersonRepository.findById(id);
    }

    @Override
    public Mono<NaturalPerson> createNaturalPerson(NaturalPerson naturalPerson) {
        if(naturalPerson == null){
            return null;
        }else{
            return naturalPersonRepository.save(naturalPerson);
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
    public Mono<Void> deleteNaturalPerson(Long id) {
        return naturalPersonRepository.deleteById(id);
    }

    @Override
    public Flux<NaturalPerson> findByDocumentNumber(Long documentNumber) {
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