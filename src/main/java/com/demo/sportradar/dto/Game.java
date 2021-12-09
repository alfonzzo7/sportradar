package com.demo.sportradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game implements Serializable {

    private Long id;

    @JsonProperty("homeTeam")
    @NotNull(message = "must not be null")
    @Valid
    private Team homeTeam;

    @JsonProperty("awayTeam")
    @NotNull(message = "must not be null")
    @Valid
    private Team awayTeam;
}
