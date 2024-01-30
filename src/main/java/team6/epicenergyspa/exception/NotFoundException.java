package team6.epicenergyspa.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
    public NotFoundException(long id ){
        super("Id " + id + " not found!");
    }

}
