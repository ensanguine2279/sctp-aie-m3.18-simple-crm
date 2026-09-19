package sg.edu.ntu.simple_crm.service;

import java.util.logging.Logger;

import org.springframework.stereotype.Service;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.exception.InvalidCustomerException;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@Service("customerServiceValidationImpl")
public class CustomerServiceValidationImpl extends CustomerServiceImpl {

    Logger logger = Logger.getLogger(CustomerServiceValidationImpl.class.getName());

    public CustomerServiceValidationImpl(CustomerRepository customerRepository) {
        super(customerRepository, null);
    }

    private void validateCustomer(Customer customer) throws InvalidCustomerException {
        logger.info("Validating customer: " + customer);

        if (customer.getFirstName() == null || customer.getFirstName().isEmpty()) {
            throw new InvalidCustomerException("Customer first  name is invalid");
        }

        if (customer.getLastName() == null || customer.getLastName().isEmpty()) {
            throw new InvalidCustomerException("Customer last name is invalid");
        }

        if (customer.getEmail() == null || customer.getEmail().isEmpty() || customer.getEmail().indexOf('@') == -1) {
            throw new InvalidCustomerException("Customer email is invalid");
        }
    }

    @Override
    public Customer createCustomer(Customer customer) throws InvalidCustomerException {
        validateCustomer(customer);
        return super.createCustomer(customer);
    }
}
