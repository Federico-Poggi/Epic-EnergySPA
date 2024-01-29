package team6.epicenergyspa.repositoriy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Customer;

@Repository
public interface CustomersDAO extends JpaRepository<Customer,Long> {
}
