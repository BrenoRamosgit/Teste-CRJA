package com.example.demo.mapper;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.model.Departament;
import com.example.demo.model.Task;
import com.example.demo.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class TaskMapper {

    @Autowired
    private DepartamentRepository repository;

    @Autowired
    private PersonMapper personMapper;



    public Task create(TaskRequest request){
        Task model = new Task();
        model.setTitle(request.getTitle());
        model.setDescription(request.getDescription());
        model.setTerm(request.getTerm());
        model.setDuration(request.getDuration());
        if(nonNull(request.getDepartamentId())){
            model.setDepartament(find(request.getDepartamentId()));
        }
        model.setFinished(Boolean.FALSE);
        model.setStartTime(LocalDateTime.now());
        return model;
    }

    public TaskResponse response(Task model){
        TaskResponse response = new TaskResponse();
        response.setId(model.getId());
        response.setTitle(model.getTitle());
        response.setDescription(model.getDescription());
        response.setTerm(model.getTerm());
        response.setDuration(model.getDuration());
        response.setFinished(model.getFinished());
        if(nonNull(model.getPerson())){
            response.setPerson(personMapper.response(model.getPerson()));
        }
        return response;
    }

    public List<TaskResponse> response(List<Task> model){
        return model.stream().map(this::response).toList();
    }

    private Departament find(Long id) {
        Optional<Departament> model = repository.findById(id);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Departamento de id %d não encontrada.", id));
        }
        return model.get();
    }
}
