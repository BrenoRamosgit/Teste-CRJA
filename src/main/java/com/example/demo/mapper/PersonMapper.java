package com.example.demo.mapper;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.model.Department;
import com.example.demo.model.Person;
import com.example.demo.repository.DepartamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Component
public class PersonMapper {

    @Autowired
    private DepartamentRepository departamentRepository;

    public Person create(PersonRequest request){
        Person model = new Person();
        model.setName(request.getName());
        if(nonNull(request.getDepartamentId())){
            model.setDepartment(find(request.getDepartamentId()));
        }
        return model;
    }

    public PersonResponse response(Person model){
        PersonResponse response = new PersonResponse();
        response.setId(model.getId());
        response.setName(model.getName());
        if(nonNull(model.getDepartment())){
            DepartamentResponse departamentResponse = new DepartamentResponse();
            departamentResponse.setId(model.getDepartment().getId());
            departamentResponse.setName(model.getDepartment().getName());
            response.setDepartament(departamentResponse);
        }
        return response;
    }

    public List<PersonResponse> response(List<Person> model){
        return model.stream().map(this::response).toList();
    }

    private Department find(Long id) {
        Optional<Department> model = departamentRepository.findById(id);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Departamento de id %d não encontrada.", id));
        }
        return model.get();
    }
}
