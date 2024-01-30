package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.model.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersDAO extends JpaRepository<Customer, Long> {

    boolean existsByVatNumber(long vatNumber);
    //ORDER
    //Use "Desc" instead of "Asc" to change the order
    List<Customer> findAllByOrderByCompanyNameAsc();

    List<Customer> findAllByOrderByAnnualTurnoverAsc();

    List<Customer> findAllByOrderByEnteringDateAsc();

    List<Customer> findAllByOrderByLastContactDateAsc();

    // TO DO TOGETHER SEEING CSV
    List<Customer> findAllByAddress_ProvinceOrderByProvinceNameAsc(String province);

    //FILTER
    List<Customer> findAllByAnnualTurnoverEquals( LocalDate annualTurnover);

    List<Customer> findAllByEnteringDateEquals(LocalDate enteringDate);

    List<Customer> findAllByLastContactDateEquals(LocalDate lastContactDate);

    List<Customer> findAllByCompanyNameContaining(String companyName);

}





