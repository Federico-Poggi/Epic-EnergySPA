package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Comuni;
@Repository
public interface ComuniDAO extends JpaRepository<Comuni,Long> {
}
