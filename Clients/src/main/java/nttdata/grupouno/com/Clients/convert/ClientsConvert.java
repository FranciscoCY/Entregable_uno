package nttdata.grupouno.com.Clients.convert;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.NaturalPerson;
import nttdata.grupouno.com.Clients.models.dto.ClientsDto;
import org.springframework.stereotype.Component;

@Component
public class ClientsConvert {
    public ClientsDto convertDTO(Clients clients){
        ClientsDto dto=new ClientsDto();
        dto.setId(clients.getId());
        dto.setIdPerson(clients.getIdPerson());
        dto.setIdTypePerson(clients.getIdTypePerson());
        return dto;
    }
}
