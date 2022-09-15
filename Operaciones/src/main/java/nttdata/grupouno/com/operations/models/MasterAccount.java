package nttdata.grupouno.com.operations.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document(collection = "cuentaOperaciones")
public class MasterAccount {
    @Id
    private Integer id;
    private String numeroCuenta;
    private String tipo;
    private Date fechaApertura;
    private char estado; // A:Activo - I:Inactivo - C:Cancelado
    private Date fechaCancelacion;
    private Double monto;
    private String tipoMoneda; // PEN - USD
}
