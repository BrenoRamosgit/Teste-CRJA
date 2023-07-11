package com.example.demo.service;

import com.example.demo.dto.request.AllocPersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<TaskResponse> list(Pageable pageable);

    List<TaskResponse> listPedingTasks();

    TaskResponse findById(Long id);

    TaskResponse create(TaskRequest request);

    TaskResponse finish(Long id);

    TaskResponse alloc(AllocPersonRequest request, Long id);

    Task find(Long id);
}
