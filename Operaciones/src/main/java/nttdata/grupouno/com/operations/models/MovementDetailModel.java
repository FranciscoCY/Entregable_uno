package nttdata.grupouno.com.operations.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import java.util.Date;

@Data
@Document(collection = "movimientoOperaciones")
public class MovementDetailModel {
    @Id
    private Integer id;
    private String numeroCuenta;
    private Date fecha;
    private Double monto;
    private Character tipoMovimiento ; // R:retiro D:deposito
    private Double comision;
    private String tipoMoneda; // PEN - USD

    public MovementDetailModel(Integer id, String numeroCuenta, Date fecha, Double monto, Character tipoMovimiento, Double comision, String tipoMoneda){
        this.id = id;
        this.numeroCuenta = numeroCuenta;
        this.fecha = fecha;
        this.monto = monto;
        this.tipoMovimiento = tipoMovimiento;
        this.comision = comision;
        this.tipoMoneda = tipoMoneda;
    }

}
