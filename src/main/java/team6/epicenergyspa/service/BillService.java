package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.model.BillStatus;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.payload.bill.NewBillDTO;
import team6.epicenergyspa.payload.bill.NewBillResponseDTO;
import team6.epicenergyspa.payload.bill.NewStatusDTO;
import team6.epicenergyspa.repository.BillsDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillsDAO billsDAO;

    @Autowired
    CustomerService cus;

    public Page<Bill> getAll(int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAll(pageable);
    }

    public Bill findByBillsNumber(long billNumber) throws NotFoundException {
        return billsDAO.findByBillNumber(billNumber)
                       .orElseThrow(() -> new NotFoundException("fattura con numero " + billNumber + " non trovata!"));
    }

    public Page<Bill> getByCompanyName(Long id, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAllByCustomer_Id(id, pageable);
    }

    public NewBillResponseDTO saveFattura(NewBillDTO bill, Long idCustomer) {
        Customer customer = cus.findById(idCustomer);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate date = LocalDate.now();
        List<Bill> listBill = billsDAO.findAll();
        long sizeList = listBill.size();
        System.out.println(date);
        Bill newBill = new Bill();
        newBill.setAmount(bill.importo());
        newBill.setDate(date);
        newBill.setBillStatus(BillStatus.WAITING);
        newBill.setBillNumber(sizeList + 1);
        newBill.setCustomer(customer);
        billsDAO.save(newBill);
        return new NewBillResponseDTO(newBill.getId(), newBill.getBillNumber());
    }

    public Page<Bill> getByState(String state, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAllByBillStatus(BillStatus.valueOf(state), pageable);
    }


    public Page<Bill> getByDate(LocalDate date, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAllByDate(date, pageable);
    }


    public Page<Bill> getByYear(int year, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAllByDateYear(year, pageable);
    }


    public Page<Bill> getByImporto(Double importo, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        return billsDAO.findAllByAmount(importo, pageable);
    }


    public NewStatusDTO statusUpdate(Long idFattura) {
        Bill bill = billsDAO.findById(idFattura)
                            .orElseThrow(() -> {throw new NotFoundException("Fattura non trovata");});
        bill.setBillStatus(BillStatus.PAYED);
        billsDAO.save(bill);
        return new NewStatusDTO(bill.getBillStatus()
                                    .name());
    }
}
