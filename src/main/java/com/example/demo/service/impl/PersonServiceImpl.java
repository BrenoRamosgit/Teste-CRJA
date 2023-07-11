package com.example.demo.service.impl;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;


    @Override
    public Page<PersonResponse> list(Pageable pageable) {
        Page<Person> model = repository.findAll(pageable);
        return model.map(mapper::responseWithTotalHours);
    }

    @Override
    public PersonResponse findById(Long id) {
        Person model = find(id);
        return mapper.response(model);
    }

    @Override
    public PersonResponse create(PersonRequest request) {
        Person model = mapper.create(request);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    public PersonResponse update(PersonRequest request, Long id) {
        Person model = find(id);
        BeanUtils.copyProperties(request, model);
        model = repository.save(model);
        return mapper.response(model);
    }

    @Override
    public void delete(Long id) {
       Person model = find(id);
       try {
           repository.delete(model);
       }
       catch (DataIntegrityViolationException e){
           throw new BaseException(HttpStatus.BAD_REQUEST, String.format("Não é possivel deletar a pessoa de id %d, a mesma já está em uso.", id));
       }

    }

    @Override
    public Person find(Long id){
        Optional<Person> model = repository.findById(id);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Pessoa de id %d não encontrada.", id));
        }
        return model.get();
    }
}
