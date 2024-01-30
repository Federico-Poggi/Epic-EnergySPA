package team6.epicenergyspa.payload.customer;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;

import java.time.LocalDate;

public record NewCustomerDTO(
        @NotEmpty(message="Company Name field can't be empty!")
        String companyName,
        @NotEmpty(message="VAT Number field can't be empty!")
        long vatNumber,
        @NotEmpty(message="Email field can't be empty!")
        String email,
        @NotEmpty(message="Entering date field can't be empty!")
        LocalDate enteringDate,
        @NotEmpty(message="Last contact field can't be empty!")
        LocalDate lastContactDate,
        @NotEmpty(message="Annual turnover field can't be empty!")
        LocalDate annualTurnover,
        @NotEmpty(message="PEC field can't be empty!")
        String pec,
        @NotEmpty(message="Phone field can't be empty!")
        long phone,
        @NotEmpty(message="Contact email field can't be empty!")
        String contactEmail,
        @NotEmpty(message="Contact surname field can't be empty!")
        String contactName,
        @NotEmpty(message="Contact surname field can't be empty!")
        String contactSurname,
        @NotEmpty(message="Contact phone field can't be empty!")
        long contactPhone,
        @NotEmpty(message="Company LOGO field can't be empty!")
        String companyLogo
) {
}
