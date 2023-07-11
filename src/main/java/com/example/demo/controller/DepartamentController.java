package com.example.demo.controller;


import com.example.demo.controller.swagger.DepartamentControllerSwagger;
import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.service.DepartamentService;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/departamentos")
public class DepartamentController implements DepartamentControllerSwagger {

    @Autowired
    private DepartamentService service;

    @GetMapping
    @Override
    public ResponseEntity<Page<DepartamentResponse>> list(Pageable pageable){
        Page<DepartamentResponse> response = service.list(pageable);
        return ResponseEntity.ok(response);
    }
}
