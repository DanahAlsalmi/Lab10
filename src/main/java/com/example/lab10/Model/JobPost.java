package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Title cannot be empty.")
    @Size(min = 4, message = "Title must be more than 4 characters.")

    @Column(columnDefinition = "varchar(20) not null")
    @Check(constraints = "LENGTH(title) > 4")
    private String title;

    @NotEmpty(message = "Description cannot be empty.")
    @Column(columnDefinition = "text not null")
    private String description;

    @NotEmpty(message = "Location cannot be empty.")
    @Column(columnDefinition = "varchar(50) not null")
    private String location;

    @NotNull(message = "Salary cannot be empty")
    @Positive(message = "Salary must be a positive value.")
    @Column(columnDefinition = "int not null")
    @Check(constraints = "salary > 0")
    private int salary;

    @Column(columnDefinition = "Date")
    private LocalDate postingDate;
}
