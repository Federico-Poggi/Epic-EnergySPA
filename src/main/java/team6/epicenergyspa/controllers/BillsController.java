package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.payload.bill.NewBillDTO;
import team6.epicenergyspa.payload.bill.NewBillResponseDTO;
import team6.epicenergyspa.payload.bill.NewStatusDTO;
import team6.epicenergyspa.service.BillService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/bills")
public class BillsController {

    @Autowired
    BillService billService;

    //filtered by cliente
    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public Page<Bill> getBillsFilteredBy(@RequestParam(required = false) Long idCustomer,
                                         @RequestParam(required = false) String state,
                                         @RequestParam(required = false) String date,
                                         @RequestParam(required = false) Integer year,
                                         @RequestParam(required = false) Double importo,
                                         @RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "5") int size,
                                         @RequestParam(defaultValue = "id") String sortedBy
                                        ) {
        if (state != null) {
            return billService.getByState(state, page, size, sortedBy);
        } else if (date != null) {
            LocalDate data = LocalDate.parse(date);
            return billService.getByDate(data, page, size, sortedBy);
        } else if (year != null) {
            return billService.getByYear(year, page, size, sortedBy);
        } else if (importo != null) {
            return billService.getByImporto(importo, page, size, sortedBy);
        } else if (idCustomer != null) {
            return billService.getByCompanyName(idCustomer, page, size, sortedBy);
        } else {
            return billService.getAll(page, size, sortedBy);
        }


    }

    @PostMapping("/{idCustomer}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public NewBillResponseDTO save(@PathVariable Long idCustomer, @RequestBody @Validated NewBillDTO billDto,

                                   BindingResult bd
                                  ) {
        if (bd.hasErrors()) {
            throw new BadRequestException("Controlla i campi inseriti");
        } else {
            return billService.saveFattura(billDto, idCustomer);
        }
    }

    @PatchMapping("/status/{idFattura}")
    @ResponseStatus(HttpStatus.OK)
    public NewStatusDTO changeStatus(@PathVariable Long idFattura) {
        return billService.statusUpdate(idFattura);
    }

}
