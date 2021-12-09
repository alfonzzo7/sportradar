package com.demo.sportradar.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Team {

    @JsonProperty("name")
    @NotBlank(message = "must not be blank")
    private String name;

    @JsonProperty("score")
    @PositiveOrZero(message = "must be greater than or equal to 0")
    private int score;

}
