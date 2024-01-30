package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.UnauthorizedException;
import team6.epicenergyspa.model.Role;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.payload.NewUserDTO;
import team6.epicenergyspa.payload.UserLoginDTO;
import team6.epicenergyspa.repository.UserDAO;
import team6.epicenergyspa.security.JWTTools;

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
        User user = usersService.findByEmail(body.email());
        if (bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide!");
        }
    }

    public User save(NewUserDTO body) {
        usersDAO.findByEmail(body.email()).ifPresent(user -> {
            throw new BadRequestException("L'email " + user.getEmail() + " è già in uso!");
        });
        User newUser = new User();
        newUser.setSurname(body.surname());
        newUser.setName(body.name());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.ADMIN);
        return usersDAO.save(newUser);
    }
}