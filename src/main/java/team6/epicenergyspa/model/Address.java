package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
    private String street;
    private String civic;
    private String location;
    private int zipCode;
    /*private String provinceName;
    private String provinceAcronym;
    private String municipalityName;
    private String companyName;*/


    @ManyToOne @JoinColumn(name = "customer_id") private Customer customer;

    @OneToOne Municipality municipality;

    public Address(String street, String civic, String location, int zipCode) {
        this.street = street;
        this.civic = civic;
        this.location = location;
        this.zipCode = zipCode;
        //
        //
    }
}
