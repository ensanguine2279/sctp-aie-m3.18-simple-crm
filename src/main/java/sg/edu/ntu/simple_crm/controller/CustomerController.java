package sg.edu.ntu.simple_crm.controller;

import jakarta.validation.Valid;

import java.time.LocalDateTime;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.entity.Interaction;
import sg.edu.ntu.simple_crm.exception.ErrorResponse;
import sg.edu.ntu.simple_crm.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody @Valid Customer customer) {
        Customer newCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
    }

    @PostMapping("/{id}/interactions")
    public ResponseEntity<Interaction> addInteractionToCustomer(
            @PathVariable Long id, @RequestBody Interaction interaction) {
        logger.info("Adding interaction to customer with ID: {}", id);
        Interaction newInteraction = customerService.addInteractionToCustomer(id, interaction);
        return new ResponseEntity<>(newInteraction, HttpStatus.CREATED);
    }

    // READ (GET ALL)
    @GetMapping("")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> allCustomers = customerService.getAllCustomers();
        return new ResponseEntity<>(allCustomers, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Customer>> searchCustomers(@RequestParam String firstName) {
        List<Customer> foundCustomers = customerService.searchCustomers(firstName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/firstNameStartingWith")
    public ResponseEntity<List<Customer>> getByFirstNameStartingWith(@RequestParam String firstName) {
        List<Customer> foundCustomers = customerService.getByFirstNameStartingWith(firstName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/firstNameAndLastName")
    public ResponseEntity<List<Customer>> getByFirstNameAndLastName(@RequestParam String firstName,
            @RequestParam String lastName) {
        List<Customer> foundCustomers = customerService.getByFirstNameAndLastName(firstName, lastName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/lastNameContaining")
    public ResponseEntity<List<Customer>> getByLastNameContaining(@RequestParam String lastName) {
        List<Customer> foundCustomers = customerService.getByLastNameContaining(lastName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/lastName")
    public ResponseEntity<List<Customer>> getByLastName(@RequestParam String lastName) {
        List<Customer> foundCustomers = customerService.getByLastName(lastName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/lastName/jpql")
    public ResponseEntity<List<Customer>> getByLastNameJPQL(@RequestParam String lastName) {
        List<Customer> foundCustomers = customerService.getByLastNameJPQL(lastName);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    @GetMapping("/search/job")
    public ResponseEntity<List<Customer>> searchCustomersByJobTitle(@RequestParam String jobTitle) {
        List<Customer> foundCustomers = customerService.searchCustomersByJobTitle(jobTitle);
        return new ResponseEntity<>(foundCustomers, HttpStatus.OK);
    }

    // READ (GET ONE)
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable Long id) {
        Customer foundCustomer = customerService.getCustomer(id);
        return new ResponseEntity<>(foundCustomer, HttpStatus.OK);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {

        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // Get all validation errors
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();

        // Build an error message string from all errors
        StringBuilder sb = new StringBuilder();
        for (ObjectError error : validationErrors) {
            sb.append(error.getDefaultMessage()).append(". ");
        }

        ErrorResponse errorResponse = new ErrorResponse(sb.toString(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
