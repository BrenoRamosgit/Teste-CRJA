package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.fixture.model.DepartamentFixture;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.model.Departament;
import com.example.demo.model.Person;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DepartamentServiceTest extends BaseTest {

    @MockBean
    private DepartamentRepository repository;

    @Autowired
    private DepartamentService service;

    @Test
    void shouldListWithSucess() {
        Departament model = from(Departament.class).gimme(DepartamentFixture.MODEL);
        Departament model1 = from(Departament.class).gimme(DepartamentFixture.MODEL);
        Departament model2 = from(Departament.class).gimme(DepartamentFixture.MODEL);
        List<Departament> result = new ArrayList<>();
        result.add(model);
        result.add(model1);
        result.add(model2);

        Pageable pageable = Pageable.ofSize(10);
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(result));
        Page<DepartamentResponse> response = service.list(pageable);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void shouldListEmpty() {
        List<Departament> result = new ArrayList<>();

        Pageable pageable = Pageable.ofSize(10);
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(result));
        Page<DepartamentResponse> response = service.list(pageable);
        Assertions.assertTrue(response.isEmpty());
    }

    @Test
    void shouldFindByIdWithSucess() {
        Departament model = from(Departament.class).gimme(DepartamentFixture.MODEL);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(model));
        Departament response = service.find(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        BaseException exception = assertThrows(
                BaseException.class,
                () -> service.find(1L),
                "Expected service.findById(1L) to throw, but it didn't"
        );
        assertEquals(exception.getMessage(), "Departamento de id 1 não encontrada.");
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
    }
}
