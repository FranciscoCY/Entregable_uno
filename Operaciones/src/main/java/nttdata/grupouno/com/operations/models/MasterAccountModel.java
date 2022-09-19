package nttdata.grupouno.com.operations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "masterAccount")
public class MasterAccountModel {
    @Id
    private String id;
    @NotEmpty
    private String numberAccount;
    private TypeModel type; //Activo - Pasivo
    private TypeModel subType; //Ahorro - Cuenta Corriente - Plazo Fijo / Personal - Empresarial
    @NotEmpty
    private String startDate;
    @NotEmpty
    private String status; // A:Activo - I:Inactivo - C:Cancelado
    @NotEmpty
    private String endDate;
    @NotNull
    @DecimalMin(value = "0.00", message = "El monto no puede ser negativo")
    private Double amount;
    @NotEmpty
    private String coinType; // PEN - USD
    private TypeModel[] clients;
    @NotNull
    @Min(0)
    private int countLimitOperation;
    @NotNull
    @DecimalMin(value = "0.00")
    private Double amountLimit;
}
