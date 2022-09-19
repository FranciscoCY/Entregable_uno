package nttdata.grupouno.com.Clients.convert;

import nttdata.grupouno.com.Clients.models.Clients;
import nttdata.grupouno.com.Clients.models.LegalPerson;
import nttdata.grupouno.com.Clients.models.dto.ClientsLegal;
import nttdata.grupouno.com.Clients.models.dto.ClientsNatural;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientsConvert {
    public ClientsLegal convertLegalDTO(Clients clients){
        ClientsLegal dto=new ClientsLegal();
        dto.setId(clients.getId());
        dto.setIdPerson(clients.getIdPerson());
        dto.setIdTypePerson(clients.getIdTypePerson());
        return dto;
    }

    public ClientsNatural convertNaturalDTO(Clients clients){
        ClientsNatural dto=new ClientsNatural();
        dto.setId(clients.getId());
        dto.setIdPerson(clients.getIdPerson());
        dto.setIdTypePerson(clients.getIdTypePerson());
        return dto;
    }

    public ClientsLegal convertDtoLegal(LegalPerson legal){
        ClientsLegal dto=new ClientsLegal();
        dto.setIdPerson(legal.getId());
        List<LegalPerson> list=new ArrayList<>();
        list.add(legal);
        dto.setLegalPersonList(list);
        return dto;
    }
}
