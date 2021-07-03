package ru.irbish.pbscoreboard.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 42)
    @NotEmpty
    private String firstName;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 42)
    @NotEmpty
    private String lastName;

    @NotNull(message = "Name cannot be null")
    @Email(message = "Email should be valid")
    @NotEmpty
    private String email;

    private String hashPassword;

    @Transient
    @NotEmpty
    @NotNull(message = "Name cannot be null")
    @Size(min = 6, max = 42)
    private String password;

}
