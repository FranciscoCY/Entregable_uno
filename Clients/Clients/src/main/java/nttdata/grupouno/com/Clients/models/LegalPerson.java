package nttdata.grupouno.com.Clients.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
@EntityScan
@Document(collection = "LegalPerson")
@Data @Builder @NoArgsConstructor  @AllArgsConstructor
public class LegalPerson {
    @org.springframework.data.annotation.Id
    private Long id;
    @Positive(message = "El Ruc debe ser mayor a cero")
    @NotEmpty(message = "El Ruc no puede ser vacio")
    private Long ruc;
    @NotEmpty(message = "La Razón Social no puede ser vacio")
    private String businessName;
}
