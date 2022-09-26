package nttdata.grupouno.com.Clients.models.dto;

import lombok.Data;
import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;

@Data
public class NaturalClients extends Clients {

    private NaturalPerson person;
    public NaturalClients(){
        super();
        person = new NaturalPerson();
    }
}
