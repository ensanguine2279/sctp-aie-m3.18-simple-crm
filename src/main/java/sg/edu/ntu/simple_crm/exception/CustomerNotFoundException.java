package sg.edu.ntu.simple_crm.exception;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(long id) {
        super("Could not find customer with id: " + id);
    }
}