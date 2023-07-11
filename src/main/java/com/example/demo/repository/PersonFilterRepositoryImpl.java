package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public class PersonFilterRepositoryImpl implements PersonFilterRepository{
    @Override
    public Page<Person> findAll(Pageable pageable, LocalDate startTime, LocalDate finishTime) {
        return null;
    }
}
