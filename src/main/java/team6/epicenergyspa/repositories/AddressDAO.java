package team6.epicenergyspa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.epicenergyspa.model.Address;

public interface AddressDAO  extends JpaRepository<Address,Long > {
}
