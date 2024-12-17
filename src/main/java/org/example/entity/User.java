package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Firstname can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Firstname must contain only alphabetic characters!")
    @Length(min = 3, max = 25)
    @Column(length = 25)
    private String firstName;

    @NotBlank(message = "LastName can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "LastName must contain only alphabetic characters!")
    @Length(min = 3, max = 50)
    @Column(length = 50)
    private String lastName;

    @NotBlank(message = "Username can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only alphabetic characters!")
    @Length(min = 3, max = 25,message = "Username must be less than 25 character!")
    @Column(length = 25)
    private String username;

    @NotBlank(message = "Password can not be null or empty!")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*[0-9])(?=.*[@#$%^&+=!]).{8,}$",
            message = "Password must contain at least one uppercase letter, " +
                    "one digit, one special character, and be at least 8 characters long!"
    )
    @Length(min = 8, max = 250)
    @Column(length = 250)
    private String password;

    @NotBlank(message = "Phone Number can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Phone Number must contain only digits!"
    )
    @Length(min = 11, max = 11, message = "Phone Number must be 11 digits!")
    @Column(length = 11)
    private Long phoneNum;

    @NotBlank(message = "National Code can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "National Code must contain only digits!"
    )
    @Length(min = 11, max = 11, message = "National Code must be 10 digits!")
    @Column(length = 11)
    private Long nationalId;

}
