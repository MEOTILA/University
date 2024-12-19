package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
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
public class Student extends Users {

    @NotBlank(message = "Student ID can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Student ID must contain only digits!"
    )
    @Length(min = 10, max = 10, message = "Student ID must be 10 digits!")
    @Column(length = 10, unique = true)
    private String studentId;
}
