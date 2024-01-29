package team6.epicenergyspa;

import jakarta.validation.constraints.NotNull;

public record MailPayloadDTO(@NotNull String toMailCustomer, @NotNull String subject, @NotNull String text) {
}
