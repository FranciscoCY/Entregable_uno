package nttdata.grupouno.com.Clients.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
@EntityScan
@Document(collection = "NaturalPerson")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class NaturalPerson {

    @org.springframework.data.annotation.Id
    private Long id;

    @Positive(message = "El Nro. Documento debe ser mayor de cero")
    private Long documentNumber;

    @Positive(message = "El Tipo Documento debe ser mayor de cero")
    private Long documentType;

    @NotEmpty(message = "Los Nombres no pueden ser vacios")
    private String names;

    @NotEmpty(message = "Los Apellidos no pueden ser vacios")
    private String lastNames;

    @NotEmpty(message = "El Genero no puede ser vacio")
    private String gender;

}
