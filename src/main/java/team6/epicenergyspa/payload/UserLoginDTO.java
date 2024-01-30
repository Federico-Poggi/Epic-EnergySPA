package team6.epicenergyspa.payload;

import jakarta.validation.constraints.NotEmpty;

public record UserLoginDTO( String email, String password){
}
