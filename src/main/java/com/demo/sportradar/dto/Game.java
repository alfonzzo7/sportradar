package com.demo.sportradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;

public class Game implements Serializable {

    @JsonProperty("homeTeamName")
    @NotBlank(message = "must not be blank")
    private String homeTeamName;

    @JsonProperty("homeTeamScore")
    @PositiveOrZero(message = "must be greater than or equal to 0")
    private int homeTeamScore;

    @JsonProperty("awayTeamName")
    @NotBlank(message = "must not be blank")
    private String awayTeamName;

    @JsonProperty("awayTeamScore")
    @PositiveOrZero(message = "must be greater than or equal to 0")
    private int awayTeamScore;

    public Game() {
    }

    public Game(String homeTeamName, int homeTeamScore, String awayTeamName, int awayTeamScore) {
        this.homeTeamName = homeTeamName;
        this.homeTeamScore = homeTeamScore;
        this.awayTeamName = awayTeamName;
        this.awayTeamScore = awayTeamScore;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }

    @Override
    public String toString() {
        return "Game{" +
                "homeTeamName='" + homeTeamName + '\'' +
                ", homeTeamScore=" + homeTeamScore +
                ", awayTeamName='" + awayTeamName + '\'' +
                ", awayTeamScore=" + awayTeamScore +
                '}';
    }
}
