package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.payload.address.NewAddressDTO;
import team6.epicenergyspa.payload.address.NewAddressResponseDTO;
import team6.epicenergyspa.repository.AddressDAO;
import team6.epicenergyspa.repository.MunicipalityDAO;

@Service
public class AddresService {

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    MunicipalityDAO munD;

    public Address getById(Long id) {
        return addressDAO.findById(id)
                         .orElseThrow(() -> {
                             throw new NotFoundException("Addresse non trovato");
                         });
    }


    public NewAddressResponseDTO save(NewAddressDTO ad, BindingResult bd) {
        try {
            if (!bd.hasErrors()) {
                Address a = new Address();
                a.setStreet(ad.street());
                a.setCivic(ad.civicNumber());
                a.setZipCode(ad.zipCode());
                a.setProvinceAbbrevation(ad.provinceAbbrevation());
                a.setMunicipality(munD.getMunicipalityByMunicipalityName(ad.municipalityName()));
                addressDAO.save(a);
                System.out.println(munD.getMunicipalityByMunicipalityName(ad.municipalityName()));
                System.out.println("Salvato");
                return new NewAddressResponseDTO(a.getId());
            } else {
                throw new BadRequestException("Errore nell'inserimento dati");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
