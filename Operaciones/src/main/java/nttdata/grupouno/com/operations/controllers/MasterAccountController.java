package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccount;
import nttdata.grupouno.com.operations.services.implementation.MasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/account")
public class MasterAccountController {
    @Autowired
    private MasterAccountServices accountServices;
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAccount(@RequestBody MasterAccount account){
        accountServices.createAccount(account);
    }
    @GetMapping(value = "/all")
    @ResponseBody
    public Flux<MasterAccount> findAllAccount(){
        return accountServices.findAllAccount();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mono<MasterAccount>> findAccountById(@PathVariable("id") Integer id){
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
    public void delete(@PathVariable("id") Integer id){
        accountServices.deleteBydId(id).subscribe();
    }
}
