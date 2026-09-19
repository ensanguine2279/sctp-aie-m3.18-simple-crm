package sg.edu.ntu.simple_crm.exception;

public class InvalidCustomerIdException extends RuntimeException {
    public InvalidCustomerIdException(Long customerId) {
        super("Invalid customer ID: " + customerId);
    }

}
