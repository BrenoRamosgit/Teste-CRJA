package com.example.demo.mapper;

import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.model.Department;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.Objects.nonNull;

@Component
public class DepartamentMapper {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private TaskMapper taskMapper;


    public DepartamentResponse response(Department model){
        DepartamentResponse response = new DepartamentResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        if(nonNull(model.getPersons())){
            response.setPerssonAmmount(model.getPersons().size());
            response.setPersons(personMapper.response(model.getPersons()));
        }
        if(nonNull(model.getTasks())){
            response.setTasks(taskMapper.response(model.getTasks()));
        }
        return response;
    }

    public List<DepartamentResponse> response(List<Department> model){
        return model.stream().map(this::response).toList();
    }
}
