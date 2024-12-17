package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lesson Name can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Lesson Name must contain only alphabetic characters!")
    @Length(min = 5, max = 25)
    @Column(length = 25)
    private String lessonName;

    @NotBlank(message = "Lesson Unit can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Lesson Unit must contain only digits!"
    )
    @Length(min = 1, max = 1, message = "Lesson Unit must be 1 digit!")
    @Column(length = 1)
    private Long lessonUnit;

    @NotBlank(message = "Lesson Capacity can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Lesson Capacity must contain only digits!"
    )
    @Length(min = 1, max = 2, message = "Lesson Capacity must be 2 digit!")
    @Column(length = 2)
    private Long lessonCapacity;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    private LocalDate startDate;
}
