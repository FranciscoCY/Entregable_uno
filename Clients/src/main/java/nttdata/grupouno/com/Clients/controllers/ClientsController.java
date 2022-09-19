package nttdata.grupouno.com.Clients.controllers;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.dto.ClientsLegal;
import nttdata.grupouno.com.Clients.models.dto.ClientsNatural;
import nttdata.grupouno.com.Clients.services.ClientsService;
import nttdata.grupouno.com.Clients.services.dto.ClientsLegalService;
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

    @GetMapping("/legal")
    public Flux<ClientsLegal> findAllLegal(){
        return clientsService.listAllClientsLegal();
    }

    @GetMapping("/legal/{id}")
    public Mono<ClientsLegal> findAllByIdLegal(@PathVariable final String id){
        System.out.println(id);
        return clientsLegalService.findAllById(id);
    }

    @GetMapping("/natural")
    public Flux<ClientsNatural> findAllNatural(){
        return clientsService.listAllClientsNatural();
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
}
