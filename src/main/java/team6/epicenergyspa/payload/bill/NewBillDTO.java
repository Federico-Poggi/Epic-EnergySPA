package team6.epicenergyspa.payload.bill;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewBillDTO(
        @NotNull(message="La data è obbligatoria nel formato :        ")
        LocalDate data,
        @NotNull(message="L'importo è obbligatorio")
        double importo,
        @NotNull(message="il numero della fattura è obbligatorio")
        long numeroFattura




) {

}
