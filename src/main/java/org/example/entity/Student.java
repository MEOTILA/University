package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Student extends Users {

    @NotBlank(message = "Student ID can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Student ID must contain only digits!"
    )
    @Size(min = 1, max = 10, message = "Student ID must be 1-10 digits!")
    @Column(length = 10, unique = true)
    private String studentId;


    @Size(max = 6, message = "A student cannot take more than 6 lessons!")
    @ManyToMany(mappedBy = "students")
    private Set<Lesson> lessons = new HashSet<>();

}
