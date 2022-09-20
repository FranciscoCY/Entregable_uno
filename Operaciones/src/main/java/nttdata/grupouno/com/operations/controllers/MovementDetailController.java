package nttdata.grupouno.com.operations.controllers;

import nttdata.grupouno.com.operations.models.MasterAccountModel;
import nttdata.grupouno.com.operations.models.MovementDetailModel;
import nttdata.grupouno.com.operations.services.implementation.MovementDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/movement")
public class MovementDetailController {

    @Autowired
    MovementDetailService movementService;

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public void createMovement(@RequestBody MovementDetailModel movement){
        movementService.createAccount(movement);
    }

    @GetMapping(value = "/all")
    @ResponseBody

    public Flux<MovementDetailModel> findAllAccount() {
        return movementService.findAllMovements();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Mono<MovementDetailModel>> findMovementById(@PathVariable("id") Integer id){
        Mono<MovementDetailModel> accountM = movementService.findById(id);
        return new ResponseEntity<>(accountM, accountM != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @GetMapping("/account/{account}")
    public ResponseEntity<Flux<MovementDetailModel>> findMovementByAccount(@PathVariable("account") String account){
        System.out.println(account);
        Flux<MovementDetailModel> fluxMov = movementService.findByAccount(account);
        return new ResponseEntity<>(fluxMov, HttpStatus.OK);
    }

    @GetMapping("/operation/check/{id}")
    public ResponseEntity<Mono<MasterAccountModel>> checkBalance(@PathVariable("account") String id){
        System.out.println(1);
        Mono<MasterAccountModel> masterAccount = movementService.checkBalance(id);
        System.out.println(2);
        return new ResponseEntity<>(masterAccount, masterAccount!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND);

    }

}
