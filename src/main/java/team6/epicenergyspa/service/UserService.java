package team6.epicenergyspa.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.exceptions.NotFoundException;
import team6.epicenergyspa.model.User;
import team6.epicenergyspa.repositories.UserDAO;

import java.util.List;

@Service
@Slf4j
public class UserService {
    @Autowired
    private UserDAO userDao;

    public User findById(long userId) {
        return userDao.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found with ID: " + userId));
    }
 //find by email
    public User findByEmail(String email) {
        return userDao.findByEmail(email)
             .orElseThrow(() -> new NotFoundException("User not found with email: " + email));
    }
    public List<User> getAllUsers() {
        return userDao.findAll();
    }


    // non so se conviene lasciarla l'update e la delate o se metterla nel auth service
    public User updateUser(User user) {
        return userDao.save(user);
    }
    public void deleteUser(long userId) {
        userDao.deleteById(userId);
    }
}
