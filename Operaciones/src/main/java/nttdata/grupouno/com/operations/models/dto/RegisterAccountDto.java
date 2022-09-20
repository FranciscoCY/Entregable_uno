package nttdata.grupouno.com.operations.models.dto;

import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.domain.EntityScan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nttdata.grupouno.com.operations.models.AccountClientModel;
import nttdata.grupouno.com.operations.models.MasterAccountModel;

@Data
@EntityScan
@AllArgsConstructor
@NoArgsConstructor
public class RegisterAccountDto {
    @NotNull
    private MasterAccountModel accountModel;
    @NotNull
    private AccountClientModel clientModel;    
}
