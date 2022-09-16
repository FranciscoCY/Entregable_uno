package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.services.implementation.MasterAccountServices;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void createAccount(@RequestBody @Valid MasterAccountModel account){
        accountServices.createAccount(account);
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
