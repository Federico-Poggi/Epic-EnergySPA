package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Municipality;
@Repository
public interface MunicipalityDAO extends JpaRepository<Municipality,Long> {
}
