package com.demo.sportradar.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="game")
public class GameEntity implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "home_team_id", referencedColumnName = "id", updatable = false)
    private TeamEntity homeTeam;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "away_team_id", referencedColumnName = "id", updatable = false)
    private TeamEntity awayTeam;

    private int totalScore;
}
