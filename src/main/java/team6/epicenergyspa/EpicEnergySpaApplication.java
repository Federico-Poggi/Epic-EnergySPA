package team6.epicenergyspa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import team6.epicenergyspa.mailsender.EmailService;

@SpringBootApplication
public class EpicEnergySpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(EpicEnergySpaApplication.class, args);
    }
}
