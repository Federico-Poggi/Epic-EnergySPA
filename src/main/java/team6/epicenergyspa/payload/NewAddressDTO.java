package team6.epicenergyspa.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record NewAddressDTO(
@NotEmpty(message = "La via è obbligatoria")
        @Size(min = 5,message = "la via deve avere minimo 5 caratteri")
    String via,
@NotEmpty(message = "Il civico è obbligatoria")
@Size(min = 5,message = "Il civico  deve avere minimo 5 caratteri")
String civico,
@NotEmpty(message = "La località è obbligatoria")
@Size(min = 5,message = "La località deve avere minimo 5 caratteri")
String localita,
@NotNull(message = "IL cap è obbligatoria")
@Size(min = 5, max=5,message = "Il cap dev'essere di 5 cifre")
int cap

// andranno aggiunti anche il comune e la provincia
) {

}
