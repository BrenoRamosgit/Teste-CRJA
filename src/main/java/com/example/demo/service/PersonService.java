package com.example.demo.service;

import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PersonService {

    Page<PersonResponse> list(Pageable pageable);

    Page<PersonResponse> listUsedHours(Pageable pageable);

    PersonResponse findById(Long id) throws BaseException;

    PersonResponse create(PersonRequest request);

    PersonResponse update(PersonRequest request, Long id);

    void delete(Long id);

    Person find(Long id);
}
