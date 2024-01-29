package team6.epicenergyspa.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException (String message){
        super(message);
    }
}
