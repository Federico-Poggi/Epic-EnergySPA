package team6.epicenergyspa.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Bill;
import team6.epicenergyspa.model.BillStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BillsDAO extends JpaRepository<Bill, Long> {
    Page<Bill> findAll(Pageable p);

    Optional<Bill> findByBillNumber(Long billNumber);

    Page<Bill> findAllByCustomer_Id(Long id, Pageable p);

    Page<Bill> findAllByBillStatus(BillStatus billStatus, Pageable p);

    Page<Bill> findAllByDate(LocalDate date, Pageable p);

    @Query("SELECT b FROM Bill b WHERE YEAR(b.date)=:date_year")
    Page<Bill> findAllByDateYear(int date_year, Pageable pageable);

    Page<Bill> findAllByAmount(double amount, Pageable p);


}

