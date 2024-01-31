package team6.epicenergyspa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import team6.epicenergyspa.exceptions.BadRequestException;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.Customer;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.payload.customer.NewCustomerDTO;
import team6.epicenergyspa.repository.CustomersDAO;
import team6.epicenergyspa.repository.UserDAO;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private CustomersDAO customersDAO;

    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    public User findById(long id) {
        return userDAO.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(long id) {
        User found = this.findById(id);
        userDAO.delete(found);
    }

    public User findByIdAndUpdate(long id, User body) {
        User found = this.findById(id);
        found.setSurname(body.getSurname());
        found.setName(body.getName());
        found.setEmail(body.getEmail());
        found.setPassword(body.getPassword());
        found.setAvatar(body.getAvatar());
        found.setName(body.getName());
        found.setRole(body.getRole());
        return userDAO.save(found);
    }


    public User findByEmail(String email) throws NotFoundException {
        return userDAO.findByEmail(email).orElseThrow(() -> new NotFoundException("Utente con email " + email + " non trovata!"));
    }


}