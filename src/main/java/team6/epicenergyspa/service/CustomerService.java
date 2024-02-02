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
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.model.CustomerType;
import team6.epicenergyspa.model.TipoSede;
import team6.epicenergyspa.payload.customer.NewCustomerDTO;
import team6.epicenergyspa.payload.customer.NewCustomerRespDTO;
import team6.epicenergyspa.repository.AddressDAO;
import team6.epicenergyspa.repository.BillsDAO;
import team6.epicenergyspa.repository.CustomersDAO;
import team6.epicenergyspa.repository.MunicipalityDAO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    private CustomersDAO customersDAO;

    @Autowired
    private AddresService addresService;

    private Cloudinary cloudinaryUploader;

    @Autowired
    private MunicipalityDAO municipalityDAO;

    @Autowired
    AddressDAO addressDAO;

    @Autowired
    BillsDAO billsDAO;

    //FIND ALL CUSTOMERS
    public Page<Customer> getCustomers(int page, int size, String orderBy) {
        if (size >= 50) size = 50;
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return customersDAO.findAll(pageable);
    }

    //GET CUSTOMER BY ID
    public Customer findById(Long id) {
        return customersDAO.findById(id)
                           .orElseThrow(() -> new NotFoundException(id));
    }

   /* public Page<Customer> filterBy() {

    }*/

    //CREATE NEW CUSTOMER
    public NewCustomerRespDTO save(NewCustomerDTO body) {

        if (customersDAO.existsByVatNumber(body.vatNumber())) {
            throw new BadRequestException("Customer with the same vatNumber is already registered!");
        }
        Address legalSite = addressDAO.findById(body.legalSite())
                                      .orElseThrow(() -> new NotFoundException("non ce"));
        Optional<Address> operativeSite = addressDAO.findById(body.operativeSite());
        Customer customer = new Customer();
        customer.setCompanyName(body.companyName());
        customer.setCustomerType(CustomerType.valueOf(body.customerType()));
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
        if (operativeSite.isPresent()) {
            Address newOperative = operativeSite.get();
            customer.getAddresses()
                    .add(newOperative);
            customer.getAddresses()
                    .add(legalSite);
            legalSite.setCustomer(customer);
            legalSite.setTipoSede(TipoSede.LEGALSITE);
            newOperative.setTipoSede(TipoSede.OPERATIVESITE);
            newOperative.setCustomer(customer);
            customersDAO.save(customer);
            addressDAO.updateAddressCustomer(customer, legalSite.getId());
            addressDAO.updateAddressCustomer(customer, newOperative.getId());
        } else {
            customer.getAddresses()
                    .add(legalSite);
            legalSite.setCustomer(customer);
            legalSite.setTipoSede(TipoSede.LEGALSITE);
            customersDAO.save(customer);
            addressDAO.updateAddressCustomer(customer, legalSite.getId());
        }
        return new NewCustomerRespDTO(customer.getId());
    }

    //DELETE A CUSTOMER
    public void FindByIdAndDeleteCustomer(long id) {

        Customer customer = this.findById(id);

        if (!customer.getAddresses().isEmpty()) {

            List<Address> addressList = customer.getAddresses();

            addressList.forEach(address -> {
                address.setCustomer(null);
                addressDAO.save(address);
            });
        }

        if (!customer.getBills().isEmpty()) {

            List<Bill> billList = customer.getBills();

            billList.forEach(bill -> {
                bill.setCustomer(null);
                billsDAO.save(bill);
            });

        }

        customersDAO.delete(customer);
    }

    //UPDATE A CUSTOMER
    public Customer FindByIdAndUpdateCustomer(long id, Customer body) {
        Customer found = this.findById(id);
        found.setCompanyName(body.getCompanyName());
        found.setCustomerType(body.getCustomerType());
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

    public Customer uploadImage(MultipartFile file, long customerId) throws IOException {
        // Upload on Cloudinary
        String url = (String) cloudinaryUploader.uploader()
                                                .upload(file.getBytes(), ObjectUtils.emptyMap())
                                                .get("url");//obtaining result from update

        Customer found = customersDAO.findById(customerId)
                                     .orElseThrow(() -> new NotFoundException("Customer not found"));

        found.setCompanyLogo(url);
        customersDAO.save(found);
        return found;
    }

    public String uploadImageString(MultipartFile body) throws IOException {
        String url = (String) cloudinaryUploader.uploader()
                                                .upload(body.getBytes(), ObjectUtils.emptyMap())
                                                .get("url");
        // save url a  db
        return url;

    }

    //QUERIES
    //ORDERING
    public List<Customer> getAllCustomersOrderedByName() {
        return customersDAO.findAllByOrderByCompanyNameAsc();
    }

    public List<Customer> getAllCustomersOrderedByAnnualTurnover() {
        return customersDAO.findAllByOrderByAnnualTurnoverAsc();
    }

    public List<Customer> getAllCustomersOrderedByEnteringDate() {
        return customersDAO.findAllByOrderByEnteringDateAsc();
    }

    public List<Customer> getAllCustomersOrderedByLastContactDate() {
        return customersDAO.findAllByOrderByLastContactDateAsc();
    }

}
