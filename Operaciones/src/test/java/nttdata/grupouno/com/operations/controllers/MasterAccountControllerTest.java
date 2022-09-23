package nttdata.grupouno.com.operations.controllers;

import lombok.RequiredArgsConstructor;
import nttdata.grupouno.com.operations.models.TypeModel;
import nttdata.grupouno.com.operations.services.IAccountClientService;
import nttdata.grupouno.com.operations.services.IMasterAccountServices;
import nttdata.grupouno.com.operations.services.ITypeAccountService;
import reactor.core.publisher.Mono;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class MasterAccountControllerTest {
    @InjectMocks
    private MasterAccountController masterAccountServices;
    @Mock
    private IMasterAccountServices accountServices;
    @Mock
    private ITypeAccountService typeAccountService;
    @Mock
    private IAccountClientService accountClientService;
    @Autowired
    private TypeModel typeModel;
    //@Autowired
    //private final WebClient webClient;

    @BeforeEach
    void init(){
        typeModel = new TypeModel("AHO1", "Ahorro", "A", 2, 0.0, 1, 0, 0.00);
        Mockito.when(typeAccountService.findById("AHO")).thenReturn(Mono.empty());
        Mockito.when(typeAccountService.findById("AHO1")).thenReturn(Mono.just(typeModel));
        

    }

    @Test
    void createAccountBank(){

    }
}
