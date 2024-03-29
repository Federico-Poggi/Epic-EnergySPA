package team6.epicenergyspa.payload.customer;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.time.LocalDate;
import java.util.List;

public record NewCustomerDTO(@NotEmpty(message = "Company Name field can't be empty!") String companyName,
                             @NotEmpty(message = "Customer type field can't be empty!") String customerType,
                             @NotNull(message = "VAT Number field can't be empty!") Long vatNumber,
                             @NotEmpty(message = "Email field can't be empty!") String email,
                             @NotNull(message = "Annual turnover field can't be empty!") double annualTurnover,
                             @NotEmpty(message = "PEC field can't be empty!") String pec,
                             @NotNull(message = "Phone field can't be empty!") Long phone,
                             @NotEmpty(message = "Contact email field can't be empty!") String contactEmail,
                             @NotEmpty(message = "Contact name field can't be empty!") String contactName,
                             @NotEmpty(message = "Contact surname field can't be empty!") String contactSurname,
                             @NotNull(message = "Contact phone field can't be empty!") Long contactPhone,
                             @NotEmpty(message = "Company LOGO field can't be empty!") String companyLogo,
                             @NotNull(message = "L'id indirizzo non puo essere vuoto") Long legalSite,
                             Long operativeSite) {
    @Override
    public Long operativeSite() {
        if (operativeSite == null) {
            return 0L;
        } else {
            return operativeSite;
        }

    }
}
