package nttdata.grupouno.com.Clients.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@EntityScan
@Document(collection = "LegalPerson")
@Data @Builder @NoArgsConstructor  @AllArgsConstructor
public class LegalPerson {
    @Id
    private Long id;

    @Positive(message = "El Ruc debe ser mayor a cero")
    private Long ruc;

    @NotEmpty(message = "La Razón Social no puede ser vacio")
    private String businessName;
}
