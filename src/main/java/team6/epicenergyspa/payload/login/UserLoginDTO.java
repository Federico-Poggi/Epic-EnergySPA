package team6.epicenergyspa.payload.login;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO( String email, String password){
}
