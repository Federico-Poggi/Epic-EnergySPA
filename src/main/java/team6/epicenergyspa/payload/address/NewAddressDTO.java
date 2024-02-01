package team6.epicenergyspa.payload.address;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

public record NewAddressDTO(@NotNull(message = "La via deve essere inserita") String street,
                            @NotNull(message = "L'indirizzo deve avere un numero civico") String civicNumber,
                            @NotNull(message = "Il comune deve essere inserito") String municipalityName,
                            @NotNull(message = "Lo zipcode deve essere inserito") int zipCode,
                            @NotNull(message = "La sigla della provincia deve essere inserita") String provinceAbbrevation,
                            @Nullable String typeSede

) {

}
