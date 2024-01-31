package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.payload.address.NewAddressDTO;
import team6.epicenergyspa.payload.address.NewAddressResponseDTO;
import team6.epicenergyspa.service.AddresService;

@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired AddresService ads;

    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id){
        return ads.getById(id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewAddressResponseDTO saveAddress(
            @RequestBody
            @Validated
            NewAddressDTO ad, BindingResult bd
                                            ) {

        return ads.save(ad,bd);
    }

}
