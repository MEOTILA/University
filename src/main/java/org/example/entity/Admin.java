package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin extends Users{
    @NotBlank(message = "Admin ID can not be null or empty!")
    @Pattern(
            regexp = "^[0-9]+$",
            message = "Admin ID must contain only digits!"
    )
    @Size(min = 1, max = 10, message = "Admin ID must be 1-10 digits!")
    @Column(length = 10, unique = true)
    private String adminId;
}
