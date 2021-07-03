package ru.irbish.pbscoreboard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tournament")
public class Tournament{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long   id;

    @NotNull(message = "Name cannot be null")
    @Size(min = 2, max = 42)
    @NotEmpty
    private String name;

    @NotEmpty
    private String date;

    private String owner;

}
