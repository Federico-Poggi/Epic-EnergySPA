package team6.epicenergyspa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import team6.epicenergyspa.mailsender.EmailService;
import team6.epicenergyspa.payload.mail.MailPayloadDTO;
import team6.epicenergyspa.payload.mail.MailResponse;

@RestController
@RequestMapping("/mail")
public class MailController {
    @Autowired
    EmailService em;

    @PostMapping("/send")
    @ResponseStatus(HttpStatus.OK)
    public MailResponse sendEmail(@RequestBody MailPayloadDTO mb) {
        return em.sendEmail(mb);
    }


}
