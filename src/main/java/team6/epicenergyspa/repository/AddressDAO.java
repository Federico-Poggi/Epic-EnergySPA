package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.model.Customer;

@Repository
public interface AddressDAO extends JpaRepository<Address, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Address a SET a.customer =:customer WHERE a.id =:id")
    void updateAddressCustomer(Customer customer, Long id);
}
