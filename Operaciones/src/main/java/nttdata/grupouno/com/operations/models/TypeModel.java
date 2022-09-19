package nttdata.grupouno.com.operations.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@EntityScan
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "typeAccount")
public class TypeModel {
    @Id
    private String code;
    private String description;
    private String status;
    private int countLimitOperation;
    private Double amountCommission;
}
