package nttdata.grupouno.com.operations.controllers;

import lombok.RequiredArgsConstructor;
import nttdata.grupouno.com.operations.repositories.implementation.MasterAccountRepository;
import nttdata.grupouno.com.operations.services.implementation.MasterAccountServices;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class MasterAccountControllerTest {
    @Mock
    private MasterAccountRepository masterAccountRepository;
    @InjectMocks
    private MasterAccountServices masterAccountServices;
    @Autowired
    private final WebClient webClient;

    @Test
    void findByClient(){

    }
}
