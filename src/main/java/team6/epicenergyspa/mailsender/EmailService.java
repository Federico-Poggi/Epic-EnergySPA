package team6.epicenergyspa.mailsender;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.payload.mail.MailPayloadDTO;

@Service
@NoArgsConstructor
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Value ("${spring.mail.username}")
    String mail;

    public void sendEmail(MailPayloadDTO mailPayloadDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(mailPayloadDTO.toMailCustomer() );
        message.setSubject(mailPayloadDTO.subject());
        message.setText(mailPayloadDTO.text());
        javaMailSender.send(message);
    }
}
