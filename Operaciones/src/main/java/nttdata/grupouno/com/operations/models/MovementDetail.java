package nttdata.grupouno.com.operations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "MovementDetail")
public class MovementDetail {
    @Id
    private Integer id;
    private String numberAccount;
    private Date date;
    private Double amount;
    private Character movementType ; // R:retiro D:deposito C:consulta
    private Double commission;
    private String currency; // PEN - USD

}
