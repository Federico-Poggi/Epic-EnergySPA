package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.service.BillService;

import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Autowired BillService billService;
    //filtered by cliente
    @GetMapping("/{idCustomer}")
    @ResponseStatus(HttpStatus.FOUND)
    @PreAuthorize("hasAuthority('ADMIN , USER')")
    public List<Bill> getBillsFilteredBy(@PathVariable Long idCustomer){
        return billService.getByCompanyName(idCustomer);
    }

}
