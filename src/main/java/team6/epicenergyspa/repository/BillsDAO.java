package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Bill;

import java.util.Optional;

@Repository
public interface BillsDAO extends JpaRepository<Bill,Long > {
    Optional<Bill> findByBillNumber(Long billNumber);
}

