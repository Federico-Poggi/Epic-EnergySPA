package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*@Component*/
@Entity
@Table(name = "municipalities")
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Municipality {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "municipality_id")
    private Long id;
    @Column(name = "province_code")
    private int provinceCode;
    @Column(name = "progressive_municipality_code")
    private int progressiveMunicipalityCode;
    @Column(name = "municipality_name")
    private String municipalityName;
    @Column(name = "province")
    private String provinceName;

    @ToString.Exclude
    @ManyToOne (cascade = CascadeType.ALL, optional = false)
    @JoinColumn (name = "province_id", nullable = false)
    private Province province;

    public Municipality(int provinceCode, int progressiveMunicipalityCode, String municipalityName, String provinceName) {
        this.provinceCode = provinceCode;
        this.progressiveMunicipalityCode = progressiveMunicipalityCode;
        this.municipalityName = municipalityName;
        this.provinceName = provinceName;
    }
}
