package com.example.demo.dto.response;

import com.example.demo.model.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartamentResponse {


    @JsonProperty("id")
    private Long id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("QuantidadePessoas")
    private Integer perssonAmmount = 0;

    @JsonProperty("pessoas")
    private List<PersonResponse> persons;

    @JsonProperty("tarefas")
    private List<TaskResponse> tasks;
}
