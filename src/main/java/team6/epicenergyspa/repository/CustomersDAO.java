package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.model.User;

import java.util.Optional;

@Repository
public interface CustomersDAO extends JpaRepository<Customer, Long> {

    boolean existsByVatNumber(long vatNumber);

}

