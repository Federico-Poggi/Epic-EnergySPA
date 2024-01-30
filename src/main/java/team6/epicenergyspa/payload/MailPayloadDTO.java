package team6.epicenergyspa.payload;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MailPayloadDTO(@NotNull @NotBlank String toMailCustomer, @NotNull @NotBlank String subject,
                             @NotNull @NotBlank String text) {
}
