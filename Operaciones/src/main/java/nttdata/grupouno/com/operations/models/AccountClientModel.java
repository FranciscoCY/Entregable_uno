package nttdata.grupouno.com.operations.models;

import javax.validation.constraints.NotEmpty;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@EntityScan
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "accountClient")
public class AccountClientModel {
    @Id
    private String id;
    @NotEmpty
    private String codeClient;
    @NotEmpty
    private String numberAccount;
    @NotEmpty
    private String typeClient; // Persona : N - Empresa: J
    @NotEmpty
    private String status;
}
