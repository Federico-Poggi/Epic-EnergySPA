package team6.epicenergyspa.payload.customer;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.model.CustomerType;

import java.time.LocalDate;
import java.util.List;

public record NewCustomerDTO(
        @NotEmpty(message="Company Name field can't be empty!")
        String companyName,
        @NotEmpty(message="Customer type field can't be empty!")
        String customerType,
        @NotNull(message="VAT Number field can't be empty!")
        Long vatNumber,
        @NotEmpty(message="Email field can't be empty!")
        String email,
        @NotNull(message="Entering date field can't be empty!")
        LocalDate enteringDate,
        @NotNull(message="Last contact field can't be empty!")
        LocalDate lastContactDate,
        @NotNull(message="Annual turnover field can't be empty!")
        LocalDate annualTurnover,
        @NotEmpty(message="PEC field can't be empty!")
        String pec,
        @NotNull(message="Phone field can't be empty!")
        Long phone,
        @NotEmpty(message="Contact email field can't be empty!")
        String contactEmail,
        @NotEmpty(message="Contact name field can't be empty!")
        String contactName,
        @NotEmpty(message="Contact surname field can't be empty!")
        String contactSurname,
        @NotNull(message="Contact phone field can't be empty!")
        Long contactPhone,
        @NotEmpty(message="Company LOGO field can't be empty!")
        String companyLogo,
        @NotNull(message="Address field can't be empty!")
        List<Address> addresses
) {

}
