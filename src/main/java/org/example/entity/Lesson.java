package org.example.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Lesson Name can not be null or empty!")
    @Pattern(regexp = "^[a-zA-Z\\\\s]+$", message = "Lesson Name must contain only alphabetic characters!")
    @Length(min = 5, max = 25)
    @Column(length = 25)
    private String lessonName;


    @NotNull(message = "Lesson Unit cannot be null!")
    @Min(value = 1, message = "Lesson Unit must be at least 1!")
    @Max(value = 9, message = "Lesson Unit must be less than 9!")
    private Integer lessonUnit;


    @NotNull(message = "Lesson Capacity cannot be null!")
    @Min(value = 1, message = "Lesson Capacity must be at least 1!")
    @Max(value = 99, message = "Lesson Capacity must be no more than 99!")
    private Integer lessonCapacity;


    @NotNull(message = "Start Date cannot be null!")
    @FutureOrPresent(message = "Start Date must be today or in the future!")
    private LocalDate startDate;


    @NotNull(message = "Teacher cannot be null!")
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;


    @Size(max = 99, message = "A lesson cannot have more than 99 students!")
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_lesson",
            joinColumns = @JoinColumn(name = "lesson_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Student> students = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("Lesson{id=%d, lessonName='%s', lessonUnit=%d, lessonCapacity=%d, startDate=%s, teacher=%s}",
                id, lessonName, lessonUnit, lessonCapacity, startDate, teacher != null ? teacher.getId() : "null");
    }

}
