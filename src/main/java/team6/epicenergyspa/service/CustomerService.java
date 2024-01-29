package team6.epicenergyspa.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import team6.epicenergyspa.exception.NotFoundException;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.payload.NewCustomerDTO;
import team6.epicenergyspa.repositoriy.CustomersDAO;

import java.io.IOException;

@Service
public class CustomerService {
    @Autowired
    private CustomersDAO customersDAO;

    private Cloudinary cloudinaryUploader;

    //FIND ALL CUSTOMERS
    public Page<Customer> getCustomers( int page, int size, String orderBy){
        if (size >= 25) size = 25;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return customersDAO.findAll(pageable);
    }
    //GET CUSTOMER BY ID
    public Customer findById( long id) {
        return customersDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }
    //CREATE NEW CUSTOMER
    public Customer save( NewCustomerDTO body ){
        Customer customer = new Customer();
        customer.setCompanyName(body.companyName());
        customer.setVatNumber(body.vatNumber());
        customer.setEmail(body.email());
        customer.setEnteringDate(body.enteringDate());
        customer.setLastContactDate(body.lastContactDate());
        customer.setAnnualTurnover(body.annualTurnover());
        customer.setPec(body.pec());
        customer.setPhone(body.phone());
        customer.setContactEmail(body.contactEmail());
        customer.setContactName(body.contactName());
        customer.setContactSurname(body.contactSurname());
        customer.setContactPhone(body.contactPhone());
        customer.setCompanyLogo(body.companyLogo());
        return customersDAO.save(customer);
    }
    //DELETE A CUSTOMER
    public void FindByIdAndDeleteCustomer(long id){
        Customer found= this.findById(id);
        customersDAO.delete(found);
    }
    //UPDATE A CUSTOMER
    public Customer FindByIdAndUpdateCustomer(long id,Customer body){
        Customer found= this.findById(id);
        found.setCompanyName(body.getCompanyName());
        found.setVatNumber(body.getVatNumber());
        found.setEmail(body.getEmail());
        found.setEnteringDate(body.getEnteringDate());
        found.setLastContactDate(body.getLastContactDate());
        found.setAnnualTurnover(body.getAnnualTurnover());
        found.setPec(body.getPec());
        found.setPhone(body.getPhone());
        found.setContactEmail(body.getContactEmail());
        found.setContactName(body.getContactName());
        found.setContactSurname(body.getContactSurname());
        found.setContactPhone(body.getContactPhone());
        found.setCompanyLogo(body.getCompanyLogo());
        return customersDAO.save(found);
    }
    public Customer uploadImage( MultipartFile file, long customerId) throws IOException {
        // Upload on Cloudinary
        String url = (String)  cloudinaryUploader.uploader()
                .upload(file.getBytes(), ObjectUtils.emptyMap())
                .get("url");//obtaining result from update

        Customer found = customersDAO.findById(customerId)
                .orElseThrow(() -> new NotFoundException("Customer not found"));

        found.setCompanyLogo(url);
        customersDAO.save(found);
        return found;
    }
}
