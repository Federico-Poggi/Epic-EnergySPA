package team6.epicenergyspa.payload.mail;

import org.springframework.http.HttpStatus;

public record MailResponse(String message, int httpStatus) {}
