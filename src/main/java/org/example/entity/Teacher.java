package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Teacher extends Users {

    @NotBlank(message = "Teacher Field can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Teacher Field  must contain only alphabetic characters!")
    @Size(min = 3, max = 50, message = "Field must be between 3 and 50 characters!")
    @Column(length = 50)
    private String teacherField;


    @NotBlank(message = "Teacher Degree can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Teacher Degree  must contain only alphabetic characters!")
    @Size(min = 3, max = 50, message = "Degree must be between 3 and 50 characters!")
    @Column(length = 50)
    private String teacherDegree;


    @NotBlank(message = "Teacher ID can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Teacher ID must contain only digits!"
    )
    @Size(min = 1, max = 10, message = "Teacher ID must be 1-10 digits!")
    @Column(length = 10, unique = true)
    private String teacherId;
}
