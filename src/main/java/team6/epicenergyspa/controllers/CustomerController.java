package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.payload.customer.NewCustomerDTO;
import team6.epicenergyspa.payload.customer.NewCustomerRespDTO;
import team6.epicenergyspa.service.CustomerService;

@RestController
@RequestMapping("/customers)")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{Id}")
    public Customer getCustomerById ( @PathVariable long id ) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewCustomerRespDTO saveEvent ( @RequestBody @Validated NewCustomerDTO payload, BindingResult validation ) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException("Error with validation during saving event.");
        } else {
            Customer newCustomer = customerService.save(payload);
            return new NewCustomerRespDTO(newCustomer.getId());
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Customer findByIdAndUpdate(@PathVariable long id, @RequestBody Customer body) {
        return this.customerService.FindByIdAndUpdateCustomer(id, body);
    }
}
