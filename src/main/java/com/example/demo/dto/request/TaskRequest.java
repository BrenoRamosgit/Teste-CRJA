package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    @JsonProperty("titulo")
    @NotNull
    public String title;

    @JsonProperty("descricao")
    @NotNull
    public String description;

    @JsonProperty("prazo")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    public LocalDate term;

    @JsonProperty("duracao")
    @NotNull
    @Min(value = 1, message = "Duração não pode ser menor que 1")
    public Integer duration;

    @JsonProperty("departamentoId")
    @NotNull
    public Long departamentId;
}
