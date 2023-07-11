package com.example.demo.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskResponse {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("titulo")
    private String title;

    @JsonProperty("descricao")
    private String description;

    @JsonProperty("prazo")
    private LocalDate term;

    @JsonProperty("duracao")
    private Integer duration;

    @JsonProperty("finalizado")
    private Boolean finished;

    @JsonProperty("pessoa")
    private PersonResponse person;


}
