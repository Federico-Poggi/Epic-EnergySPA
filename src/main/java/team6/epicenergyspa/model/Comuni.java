package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/*@Component*/
@Entity
@Table(name = "comuni")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Comuni {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id_comune")
    private Long id;
    @Column(name = "codice_provincia")
    private int codiceProivincia;
    @Column(name = "codice_progressivo_comune")
    private String codiceProgressivoComune;
    @Column(name = "nome_comune")
    private String nameComune;
    @Column(name = "provincia")
    private String provincia;

    public Comuni(int codiceProivincia, String codiceProgressivoComune, String nameComune, String provincia) {
        this.codiceProivincia = codiceProivincia;
        this.codiceProgressivoComune = codiceProgressivoComune;
        this.nameComune = nameComune;
        this.provincia = provincia;
    }
}
