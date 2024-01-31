package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private long id;
    private String street;
    private String civic;
    private String location;
    private int zipCode;
    //comune
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToOne
    Municipality municipality;
    public Address(String street, String civic, String location, int zipCode) {
        this.street = street;
        this.civic = civic;
        this.location = location;
        this.zipCode = zipCode;
        //
        //
    }
}
