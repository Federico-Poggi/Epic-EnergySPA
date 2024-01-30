package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Address;
@Repository
public interface AddressDAO  extends JpaRepository<Address,Long > {
}
