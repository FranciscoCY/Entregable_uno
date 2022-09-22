package nttdata.grupouno.com.Clients.controllers;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.MasterAccount;
import nttdata.grupouno.com.Clients.models.MovementDetail;
import nttdata.grupouno.com.Clients.models.dto.ClientsLegal;
import nttdata.grupouno.com.Clients.models.dto.ClientsNatural;
import nttdata.grupouno.com.Clients.models.dto.NaturalClients;
import nttdata.grupouno.com.Clients.services.ClientsService;
import nttdata.grupouno.com.Clients.services.dto.ClientsLegalService;
import nttdata.grupouno.com.Clients.services.dto.ClientsNaturalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/clients")
public class ClientsController {

    @Autowired
    private ClientsService clientsService;

    @Autowired
    private ClientsLegalService clientsLegalService;

    @Autowired
    private ClientsNaturalService clientsNaturalService;

    @GetMapping("/legal")
    public Flux<ClientsLegal> findAllLegal(){
        return clientsService.listAllClientsLegal();
    }

    @GetMapping("/legal/{id}")
    public Mono<ClientsLegal> findAllByIdLegal(@PathVariable final String id){
        return clientsLegalService.findAllById(id);
    }

    @GetMapping("/legal/ruc/{ruc}")
    public Mono<ClientsLegal> findAllByRuc(@PathVariable final Long ruc){
        return clientsLegalService.findByRuc(ruc);
    }

    @GetMapping("/legal/businessName/{businessName}")
    public Flux<ClientsLegal> findAllByBusinessName(@PathVariable final String businessName){
        return clientsLegalService.findByBusinessName(businessName);
    }

    @GetMapping("/natural")
    public Flux<ClientsNatural> findAllNatural(){
        return clientsService.listAllClientsNatural();
    }

    @GetMapping("/natural/{id}")
    public Mono<NaturalClients> findAllByIdNatural(@PathVariable final String id){
        return clientsNaturalService.findAllById(id);
    }

    @GetMapping("/natural/documentNumber/{documentNumber}")
    public Mono<NaturalClients> findAllByDocumentNumberNatural(@PathVariable final Long documentNumber){
        return clientsNaturalService.findByDocumentNumber(documentNumber);
    }

    @GetMapping("/natural/names/{names}")
    public Flux<NaturalClients> findAllByNamesNatural(@PathVariable final String names){
        return clientsNaturalService.findByNames(names);
    }

    @GetMapping("/natural/lastNames/{lastNames}")
    public Flux<NaturalClients> findAllByLastNamesNatural(@PathVariable final String lastNames){
        return clientsNaturalService.findByLastNames(lastNames);
    }

    @GetMapping("/{id}")
    public Mono<Clients> findAllById(@PathVariable final String id){
        return clientsService.findAllById(id).flatMap(clients -> {
            return  Mono.just(clients);
        });
    }

    @GetMapping("/findByIdTypePerson/{idTypePerson}")
    public Flux<Clients> findByIdTypePerson(@PathVariable(value ="idTypePerson" ) final Long idTypePerson){
        return clientsService.findByIdTypePerson(idTypePerson).flatMap(clients ->{
            return Flux.just(clients);
        });
    }

    @GetMapping("/findByIdPerson/{idPerson}")
    public Mono<Clients> findByIdPerson(@PathVariable(value ="idPerson" ) final String idPerson){
        return clientsService.findByIdPerson(idPerson).flatMap(clients ->{
            return Mono.just(clients);
        });
    }


    @PostMapping
    public Mono<ResponseEntity<Map<String,Object>>> addClient(@Valid @RequestBody final Mono<Clients> clientsMono){

        Map<String,Object> respuesta=new HashMap<>();
        return  clientsMono.flatMap(clients -> {
            return clientsService.createClient(clients).map(s ->{
                respuesta.put("client",s);
                return  ResponseEntity.created(URI.create("/api/clients"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(respuesta);
            });
        }).onErrorResume(ex->{
            return Mono.just(ex).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable).map(fieldError ->
                            fieldError.getDefaultMessage()).collectList()
                    .flatMap(list -> {
                        respuesta.put("errors", list);
                        return Mono.just(ResponseEntity.badRequest().body(respuesta));
                    });
        });
    }


    @PutMapping("/{id}")
    public Mono<ResponseEntity<Clients>> updateClient(@Valid @RequestBody final Clients client,@PathVariable final String id){
        return clientsService.updateClient(client,id)
                .map(c -> ResponseEntity.created(
                        URI.create("/api/clients/".concat(c.getId().toString())))
                                .contentType(MediaType.APPLICATION_JSON).body(c))
                        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteClient(@PathVariable final String id){
        return clientsService.findAllById(id).flatMap(c ->{
            return clientsService.deleteClient(c.getId())
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/natural/movement/{documentNumber}")
    public Flux<MovementDetail> findMovementByIdNatural(@PathVariable final Long documentNumber){
        return clientsNaturalService.findMovementByDocumentNumber(documentNumber);
    }

    @GetMapping("/legal/movement/{ruc}")
    public Flux<MovementDetail> findMovementByIdLegal(@PathVariable final Long ruc){
        return clientsLegalService.findMovementByRuc(ruc);
    }
    @GetMapping("/natural/account/{documentNumber}")
    public Flux<MasterAccount> findAccountByIdNatural(@PathVariable final Long documentNumber){
        return clientsNaturalService.findAccountByDocumentNumber(documentNumber);
    }

    @GetMapping("/legal/account/{ruc}")
    public Flux<MasterAccount> findAccountByIdLegal(@PathVariable final Long ruc){
        return clientsLegalService.findAccountByRuc(ruc);
    }
}
