package nttdata.grupouno.com.operations.models;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class ClientModel {
    @Id
    private String codeClient;
    private String description;
    private String status;
}
