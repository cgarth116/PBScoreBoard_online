package ru.irbish.pbscoreboard.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long    id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 42)
    @NotEmpty
    private String  name;

    @NotNull(message = "City cannot be null")
    @Size(min = 2, max = 42)
    @NotEmpty
    private String  city;

    private Integer points;

}
