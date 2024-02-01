package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.payload.address.NewAddressDTO;
import team6.epicenergyspa.payload.customer.NewCustomerDTO;
import team6.epicenergyspa.payload.customer.NewCustomerRespDTO;
import team6.epicenergyspa.service.CustomerService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable Long id) {
        return customerService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewCustomerRespDTO saveCustomer(@RequestBody @Validated NewCustomerDTO payload, BindingResult validation
                                          ) throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException("Errori nella validazione" + validation.getAllErrors());
        } else {
            //   Customer newCustomer = customerService.save(payload,validation);
            return customerService.save(payload);
        }
    }

    @PutMapping("/{id}")
    // @PreAuthorize("hasAuthority('ADMIN')")
    public Customer findByIdAndUpdate(@PathVariable long id, @RequestBody Customer body) {
        return this.customerService.FindByIdAndUpdateCustomer(id, body);
    }

    //endpoint che accetta immagini con payload NON JSON!!!  MA MULTIPLATFORM DATA , ritorna una stringa
    @PostMapping("/upload")
    public Customer uploadImage(@RequestParam("avatar") MultipartFile file, @PathVariable long customerId) throws
                                                                                                           IOException {
        return customerService.uploadImage(file, customerId);

    }

    //FOR QUERIES
    //ORDERING
    @GetMapping("/order-by-name")
    public List<Customer> getAllCustomersOrderedByName() {
        return customerService.getAllCustomersOrderedByName();
    }

    @GetMapping("/order-by-annual-turnover")
    public List<Customer> getAllCustomersOrderedByAnnualTurnover() {
        return customerService.getAllCustomersOrderedByAnnualTurnover();
    }

    @GetMapping("/order-by-entering-date")
    public List<Customer> getAllCustomersOrderedByEnteringDate() {
        return customerService.getAllCustomersOrderedByEnteringDate();
    }

    @GetMapping("/order-by-last-contact-date")
    public List<Customer> getAllCustomersOrderedByLastContactDate() {
        return customerService.getAllCustomersOrderedByLastContactDate();
    }
/*
    @GetMapping("/order-by-province")
    public List<Customer> getAllCustomersOrderedByProvince(String province) {
        return customerService.getAllCustomersOrderedByProvince(province);
    }*/

    //FILTERING
    @GetMapping("/filter-by-turnover")
    public List<Customer> getAllCustomersWithTurnoverEquals(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate annualTurnover
                                                           ) {
        return customerService.getAllCustomersWithTurnoverEquals(annualTurnover);
    }

    @GetMapping("/filter-by-entering-date")
    public List<Customer> getAllCustomersWithEnteringDateEquals(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate enteringDate
                                                               ) {
        return customerService.getAllCustomersWithEnteringDateEquals(enteringDate);
    }

    @GetMapping("/filter-by-last-contact-date")
    public List<Customer> getAllCustomersWithLastContactDateGreaterThanEqual(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate lastContactDate
                                                                            ) {
        return customerService.getAllCustomersWithLastContactDateEquals(lastContactDate);
    }

    @GetMapping("/filter-by-name")
    public List<Customer> getAllCustomersWithCompanyNameContaining(@RequestParam String partOfCompanyName) {
        return customerService.getAllCustomersWithCompanyNameContaining(partOfCompanyName);
    }

    @DeleteMapping("/{customerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteCustomer(@PathVariable long customerId) {
        customerService.FindByIdAndDeleteCustomer(customerId);
    }
}

