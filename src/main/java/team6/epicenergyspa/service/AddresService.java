package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.model.Municipality;
import team6.epicenergyspa.payload.address.NewAddressDTO;
import team6.epicenergyspa.payload.address.NewAddressResponseDTO;
import team6.epicenergyspa.repository.AddressDAO;
import team6.epicenergyspa.repository.MunicipalityDAO;

@Service
public class AddresService {

    @Autowired AddressDAO addressDAO;
    @Autowired MunicipalityDAO munD;

    public Address getById(Long id) {
        return addressDAO.findById(id).orElseThrow(() -> {
            throw new NotFoundException("Addresse non trovato");
        });
    }


    public NewAddressResponseDTO save(NewAddressDTO body, BindingResult b) {
        if (b.hasErrors()) {
            throw new BadRequestException("Controlla i campi inseriti");
        } else {
            Address a = new Address();
            Municipality m = munD.findByMunicipalityName(body.comune()).orElseThrow(() -> {
                throw new BadRequestException("Il comune inserito non esiste, cortesemente ricontrollare " +
                                                      "l'inserimento");
            });
            a.setCivic(body.civico());
            a.setStreet(body.via());
            /*a.setProvinceAcronym(body.sigla());*/
            a.setLocation(body.localita());
            a.setMunicipality(m);
            addressDAO.save(a);
            return new NewAddressResponseDTO(a.getId());
        }
    }
}
