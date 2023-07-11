package com.example.demo.controller.swagger;

import com.example.demo.dto.request.AllocPersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.dto.response.TaskResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Tag(name = "Controller de Tarefas")
public interface TaskControllerSwagger {

    @Operation(summary = "Lista Todas as tarefas", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    @PageableAsQueryParam
    ResponseEntity<Page<TaskResponse>> list(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Lista as 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    @PageableAsQueryParam
    ResponseEntity<List<TaskResponse>> listPedingTasks();

    @Operation(summary = "Busca Tarefa por id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<TaskResponse> findById(Long id);

    @Operation(summary = "Adiciona um novo registro de Tarefa", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<TaskResponse> create(TaskRequest request);

    @Operation(summary = "Finaliza uma tarefa", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<TaskResponse> finish(Long id);

    @Operation(summary = "Aloca uma tarefa a uma pessoa", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<TaskResponse> alloc(AllocPersonRequest request, Long id);
}
