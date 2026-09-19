package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@Service
public class CustomerServiceWithLoggingImpl extends CustomerServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceWithLoggingImpl.class);

    public CustomerServiceWithLoggingImpl(CustomerRepository customerRepository) {
        super(customerRepository, null);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        logger.info("CustomerServiceWithLoggingImpl.createCustomer() called");
        return super.createCustomer(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        logger.info("CustomerServiceWithLoggingImpl.getAllCustomers() called");
        return super.getAllCustomers();
    }

    @Override
    public Customer getCustomer(Long id) {
        logger.info("CustomerServiceWithLoggingImpl.getCustomer() called with id: {}", id);
        return super.getCustomer(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        logger.info("CustomerServiceWithLoggingImpl.updateCustomer() called with id: {}", id);
        return super.updateCustomer(id, customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        logger.info("CustomerServiceWithLoggingImpl.deleteCustomer() called with id: {}", id);
        super.deleteCustomer(id);
    }
}
