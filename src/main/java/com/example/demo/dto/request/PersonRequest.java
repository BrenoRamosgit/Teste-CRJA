package com.example.demo.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonRequest {

    @JsonProperty("nome")
    @NotNull(message = "Nome é obrigatório.")
    public String name;

    @JsonProperty("departamentoId")
    @NotNull(message = "Departamento é obrigatório.")
    public Long departamentId;
}
