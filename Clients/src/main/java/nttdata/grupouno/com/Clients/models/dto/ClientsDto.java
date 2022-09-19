package nttdata.grupouno.com.Clients.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientsDto extends Clients {
    List<LegalPerson> legalPersonList;

}
