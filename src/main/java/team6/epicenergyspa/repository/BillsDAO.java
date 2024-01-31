package team6.epicenergyspa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Bill;

import java.util.List;
import java.util.Optional;

@Repository
public interface BillsDAO extends JpaRepository<Bill,Long > {
    Optional<Bill> findByBillNumber(Long billNumber);

    List<Bill> findAllByCustomer_Id(Long id);

}

