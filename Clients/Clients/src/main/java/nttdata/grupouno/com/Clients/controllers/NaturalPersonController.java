package nttdata.grupouno.com.Clients.controllers;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.services.NaturalPersonService;
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
@RequestMapping("/api/naturalPerson")
public class NaturalPersonController {

    @Autowired
    NaturalPersonService naturalPersonService;

    @GetMapping
    public Flux<NaturalPerson> findAll() {
        return naturalPersonService.listAllNaturalPerson();
    }

    @GetMapping("/{id}")
    public Mono<NaturalPerson> findAllById(@PathVariable Long id) {
        return naturalPersonService.findAllById(id);
    }

    @PostMapping("/add")
    public Mono<ResponseEntity<Map<String, Object>>> addNaturalPerson(@Valid @RequestBody final Mono<NaturalPerson> person) {

        Map<String, Object> respuesta = new HashMap<>();
        return person.flatMap(p -> {

            return naturalPersonService.findByDocumentNumber(p.getDocumentNumber()).flatMap(a -> {
                respuesta.put("Persona Natural Duplicada", a);
                return Mono.just(ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(respuesta));
            }).switchIfEmpty(naturalPersonService.createNaturalPerson(p).map(s -> {
                        respuesta.put("Persona Natural Creada", s);
                        return ResponseEntity.created(URI.create("/api/clients"))
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

    @PostMapping("/update")
    Mono<NaturalPerson> updatePersona(@RequestBody NaturalPerson persona) {
        return naturalPersonService.updateNaturalPerson(persona);
    }

    @PostMapping("/delete/{id}")
    void deletePersona(@PathVariable("id") Long id) {
        naturalPersonService.deleteNaturalPerson(id).subscribe();
    }

    @GetMapping("/findByDocumentNumber/{documentNumber}")
    public Mono<NaturalPerson> findByDocumentNumber(@PathVariable Long documentNumber) {
        return naturalPersonService.findByDocumentNumber(documentNumber);
    }

    @GetMapping("/findByNames/{names}")
    public Flux<NaturalPerson> findByNames(@PathVariable String names) {
        return naturalPersonService.findByNames(names);
    }

    @GetMapping("/findByLastNames/{lastNames}")
    public Flux<NaturalPerson> findByLastNames(@PathVariable String lastNames) {
        return naturalPersonService.findByLastNames(lastNames);
    }
}
