package team6.epicenergyspa.payload.user;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import team6.epicenergyspa.model.Role;

public record NewUserDTO(
        @NotEmpty(message = "l'email è obbligatoria")
        String email,
        @NotEmpty(message = "La password è obbligatoria")
        String password,
        @NotEmpty(message = "Il nome è obbligatorio")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio")
        String surname,
        @NotEmpty(message = "Lo username è obbligatorio")
        String username,
        @NotNull(message= "il Ruolo è obbligatorio")
        Role role

) {
}
