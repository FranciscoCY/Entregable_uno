package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/account")
public class MasterAccountController {
    @Autowired
    private IMasterAccountServices accountServices;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Map<String,Object>> createAccount(@Valid @RequestBody final Mono<MasterAccountModel> account){
        Map<String,Object> response =new HashMap<>();

        return  account.flatMap(accountModel -> accountServices.createAccount(accountModel).map(s -> {
            response.put("account",s);
            return  response;
        })).onErrorResume(ex->{
            System.out.println("testeteseeee" + ex.getMessage());
            response.put("erros", ex.getMessage());
            /*return Mono.just(ex).cast(WebExchangeBindException.class)
                    .flatMap(e -> Mono.just(e.getFieldErrors()))
                    .flatMapMany(Flux::fromIterable).map(DefaultMessageSourceResolvable::getDefaultMessage).collectList()
                    .flatMap(list -> {
                        response.put("errors", list);
                        return Mono.just(ResponseEntity.badRequest().body(response));
                    });*/
            return Mono.just(response);
        });
    }
    @GetMapping(value = "/all")
    @ResponseBody
    public Flux<MasterAccountModel> findAllAccount(){
        return accountServices.findAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<MasterAccountModel>> findAccountById(@PathVariable("id") String id){
        Mono<MasterAccountModel> accountMono = accountServices.findByAccount(id);
        return new ResponseEntity<>(accountMono, accountMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MasterAccountModel> update(@RequestBody MasterAccountModel account){
        return accountServices.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id){
        accountServices.deleteBydId(id).subscribe();
    }

    @GetMapping("/findStartDate/{date}")
    @ResponseBody
    public Flux<MasterAccountModel> findStartDate(@PathVariable("date") String date){
        return accountServices.findStartDate(date);
    }
}
