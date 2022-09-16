package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccount;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import nttdata.grupouno.com.operations.services.implementation.MasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class MasterAccountController {
    @Autowired
    private MasterAccountServices accountServices;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody @Valid MasterAccount account){
        accountServices.createAccount(account);
    }
    @GetMapping(value = "/all")
    @ResponseBody
    public Flux<MasterAccount> findAllAccount(){
        return accountServices.findAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<MasterAccount>> findAccountById(@PathVariable("id") String id){
        Mono<MasterAccount> accountMono = accountServices.findByAccount(id);
        return new ResponseEntity<>(accountMono, accountMono != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public Mono<MasterAccount> update(@RequestBody MasterAccount account){
        return accountServices.updateAccount(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable("id") String id){
        accountServices.deleteBydId(id).subscribe();
    }

    @GetMapping("/findStartDate/{date}")
    @ResponseBody
    public Flux<MasterAccount> findStartDate(@PathVariable("date") String date){
        return accountServices.findStartDate(date);
    }
}
