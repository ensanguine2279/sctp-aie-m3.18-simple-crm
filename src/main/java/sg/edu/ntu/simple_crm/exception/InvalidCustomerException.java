package sg.edu.ntu.simple_crm.exception;

public class InvalidCustomerException extends RuntimeException{
    public InvalidCustomerException(String message) {
        super(message);
    }   
}
