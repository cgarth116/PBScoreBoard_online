package ru.irbish.pbscoreboard.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teamsoftournament")
public class TeamsOfTournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        id;

    private Long        idTournament;
    private String      name;
    private Character   groupName;
    private Integer     points;
}
