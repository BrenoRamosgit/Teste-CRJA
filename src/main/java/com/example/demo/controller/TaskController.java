package com.example.demo.controller;

import com.example.demo.controller.swagger.TaskControllerSwagger;
import com.example.demo.dto.request.AllocPersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TaskController implements TaskControllerSwagger {

    @Autowired
    private TaskService service;

    @GetMapping
    @Override
    public ResponseEntity<Page<TaskResponse>> list(Pageable pageable){
        Page<TaskResponse> response = service.list(pageable);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/pendentes")
    @Override
    public ResponseEntity<List<TaskResponse>> listPedingTasks(){
        List<TaskResponse> response = service.listPedingTasks();
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/{id}")
    @Override
    public ResponseEntity<TaskResponse> findById(Long id){
        TaskResponse response = service.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @Override
    public ResponseEntity<TaskResponse> create(@Valid @RequestBody TaskRequest request){
        TaskResponse response = service.create(request);
        return ResponseEntity.ok(response);
    }
    @PutMapping(path = "/{id}/finalizar")
    @Override
    public ResponseEntity<TaskResponse> finish(@PathVariable("id") Long id){
        TaskResponse response = service.finish(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping(path = "/{id}/alocar")
    @Override
    public ResponseEntity<TaskResponse> alloc(@Valid @RequestBody AllocPersonRequest request, @PathVariable("id") Long id){
        TaskResponse response = service.alloc(request, id);
        return ResponseEntity.ok(response);
    }

}
