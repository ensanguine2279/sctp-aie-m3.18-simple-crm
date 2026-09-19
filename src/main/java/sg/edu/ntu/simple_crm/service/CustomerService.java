package sg.edu.ntu.simple_crm.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.entity.Interaction;

public interface CustomerService {
    Customer createCustomer(Customer customer);

    Customer getCustomer(Long id);

    List<Customer> searchCustomers(@RequestParam String firstName);

    List<Customer> getByFirstNameStartingWith(String firstName);

    // Search by two fields at once, chained with And
    List<Customer> getByFirstNameAndLastName(String firstName, String lastName);

    // Find last names containing a given string
    List<Customer> getByLastNameContaining(String lastName);

    // Find customers by exact last name
    List<Customer> getByLastName(String lastName);

    List<Customer> getByLastNameJPQL(String lastName);

    List<Customer> searchCustomersByJobTitle(String jobTitle);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, Customer customer);

    void deleteCustomer(Long id);

    Interaction addInteractionToCustomer(Long id, Interaction interaction);
}
