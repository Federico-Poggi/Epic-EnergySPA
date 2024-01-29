package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name="customers")
@Getter
@Setter
@ToString
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    @Column(name="company_name")
    private String companyName;
    @Column(name="vat_number")
    private long vatNumber;
    @Column(name="email")
    private String email;
    @Column(name="entering_date")
    private LocalDate enteringDate;
    @Column(name="last_contact_date")
    private LocalDate lastContactDate;
    @Column(name="annual_turnover")
    private LocalDate annualTurnover;
    @Column(name="pec")
    private String pec;
    @Column(name="phone")
    private long phone;
    @Column(name="contact_email")
    private String contactEmail;
    @Column(name="contact_name")
    private String contactName;
    @Column(name="contact_surname")
    private String contactSurname;
    @Column(name="contact_phone")
    private long contactPhone;
    @Column(name="company_logo")
    private String companyLogo;



}
