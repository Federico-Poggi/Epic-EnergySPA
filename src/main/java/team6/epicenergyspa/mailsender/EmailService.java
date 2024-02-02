package team6.epicenergyspa.mailsender;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import team6.epicenergyspa.payload.mail.MailPayloadDTO;
import team6.epicenergyspa.payload.mail.MailResponse;

@Service
@NoArgsConstructor
public class EmailService {
    @Autowired
    JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String mail;

    public MailResponse sendEmail(MailPayloadDTO mailPayloadDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail);
        message.setTo(mailPayloadDTO.toMailCustomer());
        message.setSubject(mailPayloadDTO.subject());
        message.setText(mailPayloadDTO.text());
        javaMailSender.send(message);
        return new MailResponse("Mail inviata a " + mailPayloadDTO.toMailCustomer(), HttpStatus.OK.value());
    }
}
