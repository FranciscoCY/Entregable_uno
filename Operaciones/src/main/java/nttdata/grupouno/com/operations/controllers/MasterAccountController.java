package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
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
@RequestMapping("/account")
public class MasterAccountController {
    @Autowired
    private IMasterAccountServices accountServices;

    @PostMapping("/")
    public Mono<ResponseEntity<Map<String, Object>>> createAccount(
            @Valid @RequestBody final Mono<MasterAccountModel> account) {
        Map<String, Object> response = new HashMap<>();

        return account.flatMap(p -> {
            return accountServices.findByAccount(p.getNumberAccount()).flatMap(a -> {
                response.put("duplicit", a);
                return Mono.just(ResponseEntity.badRequest()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response));
            }).switchIfEmpty(accountServices.createAccount(p).map(s -> {
                response.put("account", s);
                return ResponseEntity.created(URI.create("/account/"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(response);
            }));
        }).onErrorResume(ex -> Mono.just(ex).cast(WebExchangeBindException.class)
                .flatMap(e -> Mono.just(e.getFieldErrors()))
                .flatMapMany(Flux::fromIterable).map(DefaultMessageSourceResolvable::getDefaultMessage).collectList()
                .flatMap(list -> {
                    response.put("errors", list);
                    return Mono.just(ResponseEntity.badRequest().body(response));
                }));
    }

    @GetMapping(value = "/all")
    @ResponseBody
    public Flux<MasterAccountModel> findAllAccount() {
        return accountServices.findAllAccount();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Mono<MasterAccountModel>>> findAccountById(@PathVariable("id") final String id) {
        Mono<MasterAccountModel> accountMono = accountServices.findById(id);
        return Mono.just(new ResponseEntity<>(accountMono, accountMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<MasterAccountModel>> update(@Valid @RequestBody final MasterAccountModel account,
            @PathVariable final String id) {
        return accountServices.updateAccount(account, id)
                .map(c -> ResponseEntity.created(URI.create("/account/".concat(c.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable("id") final String id) {
        return accountServices.findById(id).flatMap(c -> accountServices.deleteBydId(c.getId())
                .then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT))))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/findStartDate/{date}")
    @ResponseBody
    public Flux<MasterAccountModel> findStartDate(@PathVariable("date") final String date) {
        return accountServices.findStartDate(date);
    }
}
