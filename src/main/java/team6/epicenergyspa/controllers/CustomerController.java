package team6.epicenergyspa.controllers;

import aj.org.objectweb.asm.Type;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public NewCustomerRespDTO saveCustomer(@RequestBody @Validated NewCustomerDTO payload, BindingResult validation)
            throws BadRequestException {
        if (validation.hasErrors()) {
            throw new BadRequestException("Errori nella validazione" + validation.getAllErrors());
        } else {
            //   Customer newCustomer = customerService.save(payload,validation);
            return customerService.save(payload);
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public Customer findByIdAndUpdate(@PathVariable long id, @RequestBody Customer body) {
        return this.customerService.FindByIdAndUpdateCustomer(id, body);
    }

    //endpoint che accetta immagini con payload NON JSON!!!  MA MULTIPLATFORM DATA , ritorna una stringa


    @PostMapping("/upload")
    public Customer uploadImage(@RequestParam("avatar") MultipartFile file, @PathVariable long customerId)
            throws IOException {
        return customerService.uploadImage(file, customerId);

    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<Customer> getCustomer(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "5") int size,
                                      @RequestParam(defaultValue = "id") String sortedBy) {
        return customerService.getCustomers(page, size, sortedBy);
    }

    //FOR QUERIES
    //ORDERING

    //Bisogna farsi inserire dal frontend il parametro che puo essere numero o stringa o data
   /* @GetMapping("/filter")
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Customer> filteredBy(@RequestParam(defaultValue = "0") int page,
                                     @RequestParam(defaultValue = "5") int size,
                                     @RequestParam(defaultValue = "id") String sortedBy,
                                     @RequestParam(required = true) String filteredBy,
                                    @RequestParam(required = false) Double annualTurnover) {
       *//* if(annualTurnover!=null){
            return customerService.filterByTurnover(annualTurnover,page,size,sortedBy);
        }else{

        }*//*
    }*/


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

}

