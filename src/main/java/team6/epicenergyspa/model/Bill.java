package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private LocalDate date;
    private double amount;
    private long billNumber;
    @Enumerated(EnumType.STRING)
    private BillStatus billStatus;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Bill(LocalDate date, double amount, long billNumber, BillStatus billStatus) {
        this.date = date;
        this.amount = amount;
        this.billNumber = billNumber;
        this.billStatus = billStatus;
    }
}
