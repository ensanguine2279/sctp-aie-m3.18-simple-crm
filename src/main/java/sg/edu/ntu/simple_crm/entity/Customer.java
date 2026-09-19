package sg.edu.ntu.simple_crm.entity;

import java.util.List;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonPropertyOrder({ "id", "firstName", "lastName", "email", "contactNo", "jobTitle", "yearOfBirth" })
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    @Column(name = "id")
    @Positive(message = "Customer ID must be positive")
    private Long id;

    @Column(name = "first_name")
    @NotBlank(message = "First name is mandatory")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    @Email(message = "Email should be valid")
    private String email;

    @Column(name = "contact_no")
    @Pattern(regexp = "\\d{8}", message = "Contact number must be exactly 8 digits long")
    private String contactNo;

    @Column(name = "job_title")
    @Size(min = 2, max = 50, message = "Job title must be between 2 and 50 characters long")
    private String jobTitle;

    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<Interaction> interactions;

    public Customer(String firstName, String lastName, String jobTitle) {
        setFirstName(firstName);
        setLastName(lastName);
        setJobTitle(jobTitle);
    }

    public Customer(String firstName, String lastName) {
        setFirstName(firstName);
        setLastName(lastName);
    }
}
