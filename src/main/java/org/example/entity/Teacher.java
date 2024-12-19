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
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Teacher extends Users {

    @NotBlank(message = "Teacher Field can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Teacher Field  must contain only alphabetic characters!")
    @Size(min = 5, max = 50, message = "Field must be between 5 and 50 characters!")
    @Column(length = 50)
    private String teacherField;


    @NotBlank(message = "Teacher Degree can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Teacher Degree  must contain only alphabetic characters!")
    @Size(min = 5, max = 50, message = "Degree must be between 5 and 50 characters!")
    @Column(length = 50)
    private String teacherDegree;


    @NotBlank(message = "Teacher ID can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Teacher ID must contain only digits!"
    )
    @Size(min = 10, max = 10, message = "Teacher ID must be 10 digits!")
    @Column(length = 10, unique = true)
    private String teacherId;
}
