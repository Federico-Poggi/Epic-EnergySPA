package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.payload.login.UserLoginDTO;
import team6.epicenergyspa.payload.login.UserLoginResponseDTO;
import team6.epicenergyspa.payload.user.NewUserDTO;
import team6.epicenergyspa.payload.user.NewUserResponseDTO;
import team6.epicenergyspa.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public UserLoginResponseDTO login(@RequestBody UserLoginDTO body) {
        String accessToken = authService.authenticateUser(body);
        return new UserLoginResponseDTO(accessToken);
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponseDTO createUser(@RequestBody @Validated NewUserDTO newUserPayload, BindingResult validation)
            throws BadRequestException {
        System.out.println(validation);
        if (validation.hasErrors()) {
            System.out.println(validation.getAllErrors());
            throw new BadRequestException("Invalid request payload" + validation.getErrorCount());
        } else {
            return authService.save(newUserPayload);
        }
    }
}
