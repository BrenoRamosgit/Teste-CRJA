package com.example.demo.service.impl;

import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.mapper.DepartamentMapper;
import com.example.demo.model.Department;
import com.example.demo.model.Person;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.service.DepartamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartamentServiceImpl implements DepartamentService {

    @Autowired
    private DepartamentRepository repository;

    @Autowired
    private DepartamentMapper mapper;

    @Override
    public Page<DepartamentResponse> list(Pageable pageable) {
        Page<Department> model = repository.findAll(pageable);
        return model.map(mapper::response);
    }

    @Override
    public Department find(Long id) {
        Optional<Department> model = repository.findById(id);
        if(model.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, String.format("Departamento de id %d não encontrada.", id));
        }
        return model.get();
    }
}
