package team6.epicenergyspa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import team6.epicenergyspa.entities.User;
import java.util.Optional;
import java.util.UUID;

public interface UserDAO extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}