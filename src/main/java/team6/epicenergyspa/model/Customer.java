package team6.epicenergyspa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "customer_type")
    @Enumerated(EnumType.STRING)
    private CustomerType customerType;

    @Column(name = "vat_number")
    private long vatNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "entering_date")
    private LocalDate enteringDate;

    @Column(name = "last_contact_date")
    private LocalDate lastContactDate;

    @Column(name = "annual_turnover")
    private Double annualTurnover;

    @Column(name = "pec")
    private String pec;

    @Column(name = "phone")
    private long phone;

    @Column(name = "contact_email")
    private String contactEmail;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_surname")
    private String contactSurname;

    @Column(name = "contact_phone")
    private long contactPhone;

    @Column(name = "company_logo")
    private String companyLogo;

    @OneToMany
    @JsonInclude
    @JsonManagedReference
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "customer")
    @JsonManagedReference
    private List<Bill> bills = new ArrayList<>();
}
