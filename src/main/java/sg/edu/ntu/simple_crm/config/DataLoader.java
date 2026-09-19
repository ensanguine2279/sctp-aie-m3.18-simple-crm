package sg.edu.ntu.simple_crm.config;

import jakarta.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import sg.edu.ntu.simple_crm.entity.Customer;
import sg.edu.ntu.simple_crm.repository.CustomerRepository;

@Component
public class DataLoader {
    private CustomerRepository customerRepository;

    public DataLoader(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @PostConstruct
    public void loadData() {
        // Clear the database first
        customerRepository.deleteAll();

        // Load seed data
        customerRepository.save(new Customer("Tony", "Stark", "CEO"));
        customerRepository.save(new Customer("Bruce", "Banner", "Scientist"));
        customerRepository.save(new Customer("Peter", "Parker", "Photographer"));
        customerRepository.save(new Customer("Stephen", "Strange", "Doctor"));
        customerRepository.save(new Customer("Natasha", "Romanoff", "Spy"));
        customerRepository.save(new Customer("Clint", "Barton", "Archer"));
        customerRepository.save(new Customer("Wanda", "Maximoff", "Sorceress"));
        customerRepository.save(new Customer("Steve", "Rogers", "Soldier"));
        customerRepository.save(new Customer("Thor", "Odinson", "God of Thunder"));
        customerRepository.save(new Customer("Loki", "Laufeyson", "God of Mischief"));
        customerRepository.save(new Customer("Morgan", "Stark", "Daughter of Ironman"));
    }
}
