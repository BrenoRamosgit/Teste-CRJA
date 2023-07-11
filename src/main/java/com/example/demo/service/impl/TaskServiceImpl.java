package com.example.demo.service.impl;

import com.example.demo.dto.request.AllocPersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.PersonService;
import com.example.demo.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskMapper mapper;

    @Autowired
    private TaskRepository repository;

    @Autowired
    private PersonService personService;


    @Override
    public Page<TaskResponse> list(Pageable pageable) {
        Page<Task> model = repository.findAll(pageable);
        return model.map(mapper::response);
    }

    @Override
    public List<TaskResponse> listPedingTasks() {
        List<Task> model = repository.listPendingTasks();
        return mapper.response(model);
    }

    @Override
    public TaskResponse findById(Long id) {
        Task model = find(id);
        return mapper.response(model);
    }

    @Override
    public TaskResponse create(TaskRequest request) {
        Task model = mapper.create(request);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    public TaskResponse finish(Long id) {
        Task model = find(id);
        if(model.getFinished().equals(Boolean.TRUE)){
            throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Tarefa de id %d Já foi finalizada.", id));
        }
        model.setFinishTime(LocalDateTime.now());
        model.setFinished(Boolean.TRUE);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    public TaskResponse alloc(AllocPersonRequest request, Long id) {
        Person person = personService.find(request.personId);
        Task model = find(id);
        if(model.getFinished().equals(Boolean.TRUE)){
            throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Tarefa de id %d Já foi finalizada.", id));
        }
        if(!model.getDepartment().getId().equals(person.getDepartment().getId())){
            throw new BaseException(HttpStatus.BAD_REQUEST, "Pessoa não pode ser associada a tarefa pois o departamento é diferente.");
        }
        model.setPerson(person);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    public Task find(Long id){
        Optional<Task> model = repository.findById(id);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Tarefa de id %d não encontrada.", id));
        }
        return model.get();
    }

    private void validateRequest(TaskRequest request){
        LocalDate currentDate = LocalDate.now();
        if(request.getTerm().isBefore(currentDate)){
            throw new BaseException(HttpStatus.BAD_REQUEST, "Prazo não pode ser menor que a data atual.");
        }
    }
}
