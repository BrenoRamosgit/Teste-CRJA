package com.example.demo.controller;

import com.example.demo.controller.swagger.PersonControllerSwagger;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pessoas")
public class PersonController implements PersonControllerSwagger {

    @Autowired
    private PersonService service;

    @GetMapping
    public ResponseEntity<Page<PersonResponse>> list(Pageable pageable){
        Page<PersonResponse> response = service.list(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> findById(@PathVariable("id") Long id){
        PersonResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<PersonResponse> create(@Valid @RequestBody PersonRequest request){
        PersonResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> update(@Valid @RequestBody PersonRequest request, @PathVariable("id") Long id){
        PersonResponse response = service.update(request, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
