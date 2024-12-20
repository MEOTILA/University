package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass

public abstract class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only alphabetic characters!")
    @Size(min = 3, max = 25, message = "First name must be between 3 and 25 characters!")
    @Column(length = 25)
    private String firstName;


    @NotBlank(message = "LastName can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName must contain only alphabetic characters!")
    @Size(min = 3, max = 50, message = "Last name must be between 3 and 50 characters!")
    @Column(length = 50)
    private String lastName;


    @NotBlank(message = "Username can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only alphabetic characters!")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters!")
    @Column(length = 25, unique = true)
    private String username;


    @NotBlank(message = "Password can not be null or empty!")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least one uppercase letter, " +
                    "one digit, one special character, and be at least 8 characters long!"
    )
    @Size(min = 8, max = 250)
    @Column(length = 250)
    private String password;


    @NotBlank(message = "Phone Number can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Phone Number must contain only digits!"
    )
    @Size(min = 11, max = 11, message = "Phone number must be 11 digits!")
    @Column(length = 11, unique = true)
    private String phoneNumber;


    @NotBlank(message = "Email can not be null or empty!")
    @Email(regexp = "^(.+)@(.+)$",message = "Invalid email format!")
    @Column(unique = true)
    private String email;


    @NotBlank(message = "National Code can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "National Code must contain only digits!"
    )
    @Size(min = 10, max = 10, message = "National ID must be 10 digits!")
    @Column(length = 10, unique = true)
    private String nationalId;

}
