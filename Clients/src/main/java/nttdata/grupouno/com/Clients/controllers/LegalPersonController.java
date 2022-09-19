package nttdata.grupouno.com.Clients.controllers;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.services.ClientsService;
import nttdata.grupouno.com.Clients.services.LegalPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/legalPerson")
public class LegalPersonController {

    @Autowired
    private LegalPersonService legalPersonService;

    @Autowired
    private ClientsService clientsService;

    @GetMapping
    public Flux<LegalPerson> findAll() {
        return legalPersonService.listAllLegalPerson();
    }

    @GetMapping("/{id}")
    public Mono<LegalPerson> findAllById(@PathVariable final String id) {
        return legalPersonService.findAllById(id).flatMap(l -> {
            return Mono.just(l);
        });
    }

    @GetMapping("/ruc/{ruc}")
    public Mono<LegalPerson> findAllByRuc(@PathVariable final Long ruc) {
        return legalPersonService.findByRuc(ruc).flatMap(l -> {
            return Mono.just(l);
        });
    }

    @GetMapping("/businessName/{name}")
    public Flux<LegalPerson> findAllByBusinessName(@PathVariable final String name) {
        return legalPersonService.findByBusinessName(name).flatMap(l -> {
            return Mono.just(l);
        });
    }

    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> addLegalPerson(@Valid @RequestBody final Mono<LegalPerson> legalPersonMono) {
        Map<String, Object> respuesta = new HashMap<>();
        return legalPersonMono.flatMap(legalPerson -> {
            return legalPersonService.findByRuc(legalPerson.getRuc()).flatMap(a -> {
                respuesta.put("Persona Juridica Duplicada", a);
                return Mono.just(ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(respuesta));
            }).switchIfEmpty(legalPersonService.createLegalPerson(legalPerson).map(l -> {
                        respuesta.put("client", l);
                        return ResponseEntity.created(URI.create("/api/legalPerson"))
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(respuesta);
                    })
            );
        }).onErrorResume(ex -> {
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
    public Mono<ResponseEntity<LegalPerson>> updateLegalPersona(@Valid @RequestBody final LegalPerson legalPerson, @PathVariable final String id) {
        return legalPersonService.updateLegalPerson(legalPerson, id)
                .map(l -> ResponseEntity.created(
                                URI.create("/api/legalPerson/".concat(l.getId().toString())))
                        .contentType(MediaType.APPLICATION_JSON).body(l))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteLegalPerson(@PathVariable final String id) {
        return legalPersonService.findAllById(id).flatMap(l -> {
            return legalPersonService.deleteLegalPerson(l.getId())
                    .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
        }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));

    }
}
