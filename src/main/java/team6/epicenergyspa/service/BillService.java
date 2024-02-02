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
        Page<Bill> pageBill = billsDAO.findAllByCustomer_Id(id, pageable);
        if (pageBill.isEmpty()) {
            throw new NotFoundException("Non sono presenti fatture con l'id inserito");
        } else {
            return pageBill;
        }
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
        return new NewBillResponseDTO(newBill.getId(), newBill.getBillNumber(), customer.getId());
    }

    public Page<Bill> getByState(String state, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        Page<Bill> pageBill = billsDAO.findAllByBillStatus(BillStatus.valueOf(state), pageable);
        if (pageBill.isEmpty()) {
            throw new NotFoundException("Non sono presenti fatture con lo stato inserito");
        } else {
            return pageBill;
        }
    }


    public Page<Bill> getByDate(LocalDate date, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        Page<Bill> pageBill = billsDAO.findAllByDate(date, pageable);
        if (pageBill.isEmpty()) {
            throw new NotFoundException("Non sono presenti fatture con la data inserita");
        } else {
            return pageBill;
        }
    }


    public Page<Bill> getByYear(int year, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        Page<Bill> pageBill = billsDAO.findAllByDateYear(year, pageable);
        if (pageBill.isEmpty()) {
            throw new NotFoundException("Non sono presenti fatture con l'anno inserito");
        } else {
            return pageBill;
        }
    }


    public Page<Bill> getByImporto(Double importo, int page, int size, String sortedBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortedBy));
        Page<Bill> pageBill = billsDAO.findAllByAmount(importo, pageable);
        if (pageBill.isEmpty()) {
            throw new NotFoundException("Non sono presenti fatture con l'importo inserito");
        } else {
            return pageBill;
        }
    }


    public NewStatusDTO statusUpdate(Long idFattura) {
        Bill bill = billsDAO.findById(idFattura)
                            .orElseThrow(() -> {throw new NotFoundException("Fattura non trovata");});
        bill.setBillStatus(BillStatus.PAYED);
        billsDAO.save(bill);
        return new NewStatusDTO(bill.getBillStatus()
                                    .name());
    }

    public void deleteBill(long idFattura) {
        Bill bill = billsDAO.findById(idFattura)
                            .orElseThrow(() -> new NotFoundException("Fattura con id " + idFattura + " non trovata " + "nel database"));
        billsDAO.delete(bill);
    }
}
