package nttdata.grupouno.com.Clients.controllers;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.services.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
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


    @GetMapping
    public Mono<ResponseEntity<Flux<Clients>>> findAll(){
        return Mono
                .just(ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(clientsService.listAllClients()))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String,Object>>> addClient(@Valid @RequestBody final Mono<Clients> clientsMono){

        Map<String,Object> respuesta=new HashMap<>();
        return  clientsMono.flatMap(clients -> {
            System.out.println(clients);
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

}
