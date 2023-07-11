package com.example.demo.controller.swagger;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
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

@Tag(name = "Controller de Pessoas")
public interface PersonControllerSwagger {

    @Operation(summary = "Lista Todas as Pessoas", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    @PageableAsQueryParam
    ResponseEntity<Page<PersonResponse>> list(@Parameter(hidden = true) Pageable pageable);

    @Operation(summary = "Busca Pessoa por id", method = "GET")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> findById(Long id);

    @Operation(summary = "Adiciona um novo registro de Pessoa", method = "POST")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> create(PersonRequest request);

    @Operation(summary = "Atualiza uma Pessoa existente", method = "PUT")
    @ApiResponses({
            @ApiResponse(responseCode = "200",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PersonResponse.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<PersonResponse> update(PersonRequest request, Long id);

    @Operation(summary = "Deleta uma Pessoa existente", method = "DELETE")
    @ApiResponses({
            @ApiResponse(responseCode = "204",content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "Requisição com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = Void.class)) }, description = "O recurso não foi encontrado") })
    ResponseEntity<Void> delete(Long id);
}
