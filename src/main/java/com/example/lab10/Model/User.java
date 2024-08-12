package com.example.lab10.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Check;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Name cannot be empty.")
    @Size(min = 4 , message = "Length must be more than 4 characters.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Username must contain only letters")
    @Column(columnDefinition = "varchar(15) not null")
    @Check(constraints = "LENGTH(name) > 4")
    @Check(constraints = "name ~ '^[a-zA-Z]+$'")
    private String name;

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email cannot be empty.")

    @Column(columnDefinition = "varchar(40) not null unique")
    @Check(constraints = "email LIKE '%@%'")
    private String email;

    @NotEmpty(message = "Password cannot be empty.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")

    @Column(columnDefinition = "varchar(15) not null")
    @Check(constraints = "LENGTH(password) >= 8")
    private String password;

    @NotNull(message = "Age cannot be null.")
    @Min(value = 21, message = "Age must be at least 21.")
//    @Pattern(regexp = "^(0|[0-9]*)$", message = "Age must contain only numbers.")

    @Column(columnDefinition = "int not null")
    @Check(constraints = "age >= 21")
//    @Check(constraints = "age ~ '^(0|[0-9]*)$'")
    private int age;

    @NotEmpty(message = "Role cannot be empty.")
    @Pattern(regexp = "JOB_SEEKER|EMPLOYER" ,message = "Must be either 'JOB_SEEKER' or 'EMPLOYER' only.")
    @Column(columnDefinition = "varchar(11) not null")
    @Check(constraints = "role IN ('JOB_SEEKER', 'EMPLOYER')")
    private String role;

}
