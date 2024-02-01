package team6.epicenergyspa.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String street;

    private String civic;

    private int zipCode;

    private String provinceAbbrevation;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference
    private Customer customer;

    @ManyToOne
    @JsonBackReference
    Municipality municipality;

    /*public Address(String street,
                   String civic,
                   String location,
                   int zipCode
                  ) {
        this.street = street;
        this.civic = civic;
        this.location = location;
        this.zipCode = zipCode;
        //
        //
    }*/
}
