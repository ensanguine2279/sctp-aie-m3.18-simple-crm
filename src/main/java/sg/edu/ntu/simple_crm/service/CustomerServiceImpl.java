package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.entity.Interaction;
import sg.edu.ntu.simple_crm.exception.CustomerNotFoundException;
import sg.edu.ntu.simple_crm.exception.InvalidCustomerIdException;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;
import sg.edu.ntu.simple_crm.repository.InteractionRepository;

@Service
@Primary
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;
    private InteractionRepository interactionRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, InteractionRepository interactionRepository) {
        this.customerRepository = customerRepository;
        this.interactionRepository = interactionRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customer.getId() != null && customer.getId() <= 0) {
            throw new InvalidCustomerIdException(customer.getId());
        }
        return customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(Long id) {
        if (id <= 0) {
            throw new InvalidCustomerIdException(id);
        }
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public List<Customer> searchCustomers(@RequestParam String firstName) {
        return customerRepository.findByFirstName(firstName);
    }

    @Override
    public List<Customer> getByFirstNameStartingWith(String firstName) {
        return customerRepository.findByFirstNameStartingWith(firstName);
    }

    @Override
    public List<Customer> getByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName);
    }

    @Override
    public List<Customer> getByLastNameContaining(String lastName) {
        return customerRepository.findByLastNameContaining(lastName);
    }

    @Override
    public List<Customer> getByLastName(String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @Override
    public List<Customer> getByLastNameJPQL(String lastName) {
        return customerRepository.findByLastNameJPQL(lastName);
    }

    @Override
    public List<Customer> searchCustomersByJobTitle(String jobTitle) {
        return customerRepository.findByJobTitleJPQL(jobTitle);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {

        // Retrieve the customer from the database
        Customer customerToUpdate = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));

        // Update the fields
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setContactNo(customer.getContactNo());
        customerToUpdate.setJobTitle(customer.getJobTitle());
        customerToUpdate.setYearOfBirth(customer.getYearOfBirth());

        // Save and return the updated customer
        return customerRepository.save(customerToUpdate);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        customerRepository.deleteById(id);
    }

    @Override
    public Interaction addInteractionToCustomer(Long id, Interaction interaction) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
        interaction.setCustomer(customer);
        return interactionRepository.save(interaction);
    }
}
