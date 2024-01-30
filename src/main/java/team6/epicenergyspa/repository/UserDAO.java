package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import team6.epicenergyspa.model.Address;
import team6.epicenergyspa.model.User;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User,Long > {
    Optional<User> findByEmail(String email);
}
