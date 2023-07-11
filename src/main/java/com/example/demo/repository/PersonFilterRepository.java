package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;

public interface PersonFilterRepository {

    Page<Person> findAll(Pageable pageable, LocalDate startTime, LocalDate finishTime, String title);
}
