package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.repository.BillsDAO;

import java.util.List;

@Service
public class BillService {
    @Autowired
    private BillsDAO billsDAO;

    public Bill findByBillsNumber(long billNumber) throws NotFoundException {
        return billsDAO.findByBillNumber(billNumber).orElseThrow(() -> new NotFoundException("fattura con numero " + billNumber + " non trovata!"));
    }

    public List<Bill> getByCompanyName(Long id){
        return billsDAO.findAllByCustomer_Id(id);
    }



}
