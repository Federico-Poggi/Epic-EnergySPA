package team6.epicenergyspa.payload.bill;

import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import team6.epicenergyspa.repository.BillsDAO;

import java.time.LocalDate;

public record NewBillDTO(@NotNull(message = "L'importo è obbligatorio") double importo
        /*@NotNull(message = "il numero della fattura è obbligatorio") long numeroFattura*/


) {

}
