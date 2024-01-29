package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "province")
@Setter
@Getter
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(name = "sigla_provincia")
    String siglaProvincia;
    @Column(name = "provincia")
    String provincia;
    @Column(name = "regione")
    String regione;

}
