package nttdata.grupouno.com.Clients.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Data
public class NaturalClients extends Clients {

    private NaturalPerson person;
    public NaturalClients(){
        super();
        person = new NaturalPerson();
    }
}
