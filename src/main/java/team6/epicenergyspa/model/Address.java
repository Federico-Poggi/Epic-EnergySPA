package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@ToString
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address  {
    //implements UserDetails
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private UUID id;
    private String via;
    private String civico;
    private String località;
    private int cap;
    //comune
    // @ManyToOne
    // @JoinColumn(name = "customer")
    // private Customer customer;

    public Address(String via, String civico, String località, int cap) {
        this.via = via;
        this.civico = civico;
        this.località = località;
        this.cap = cap;
    }
}
