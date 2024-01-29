package team6.epicenergyspa.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "provinces")
@Setter
@Getter
@NoArgsConstructor
public class Province {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;
    @Column(name = "province_abbreviation")
    private String provinceAbbreviation;
    @Column(name = "province")
    private String province;
    @Column(name = "region")
    private String region;

}
