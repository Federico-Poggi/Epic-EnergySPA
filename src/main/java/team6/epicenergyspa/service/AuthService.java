package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.UnauthorizedException;
import team6.epicenergyspa.model.Role;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.payload.user.NewUserDTO;
import team6.epicenergyspa.payload.login.UserLoginDTO;
import team6.epicenergyspa.payload.user.NewUserResponseDTO;
import team6.epicenergyspa.repository.UserDAO;
import team6.epicenergyspa.security.JWTTools;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserService usersService;

    @Autowired
    private UserDAO usersDAO;

    @Autowired
    private PasswordEncoder bcrypt;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body) {
        System.out.println(body.email() + body.password());
        User user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public NewUserResponseDTO save(NewUserDTO body) throws BadRequestException {
        try {
            Optional<User> found = usersDAO.findByEmail(body.email());
            if (!found.isPresent()) {
                User newUser = new User();
                newUser.setSurname(body.surname());
                newUser.setName(body.name());
                newUser.setEmail(body.email());
                newUser.setUsername(body.username());
                newUser.setPassword(bcrypt.encode(body.password()));
                //da cambiare prossimamente in Role.USER
                newUser.setRole(Role.ADMIN);
                usersDAO.save(newUser);
                return new NewUserResponseDTO(newUser.getId());
            } else {
                throw new BadRequestException("L'email " + body.email() + " è già in uso!");
            }
        } catch (RuntimeException e) {
            throw new BadRequestException("L'email " + body.email() + " è già in uso!");
        }

    }
}