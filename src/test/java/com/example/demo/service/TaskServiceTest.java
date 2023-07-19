package com.example.demo.service;

import com.example.demo.BaseTest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.exception.BaseException;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.fixture.model.TaskFixture;
import com.example.demo.mapper.DepartamentMapper;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.TaskRepository;
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
import java.util.List;
import java.util.Optional;

import static br.com.six2six.fixturefactory.Fixture.from;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskServiceTest extends BaseTest {

    @MockBean
    private TaskRepository repository;

    @Autowired
    private TaskService service;

    @Test
    void testFindByIdWithSucess() {
        Task model = from(Task.class).gimme(TaskFixture.MODEL);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(model));
        TaskResponse response = service.findById(1L);
        Assertions.assertNotNull(response);
    }

    @Test
    void testFindByIdNotFound() {
        doReturn(Optional.empty()).when(repository).findById(1L);
        BaseException exception = assertThrows(
                BaseException.class,
                () -> service.findById(1L)
        );
        assertEquals(exception.getMessage(), "Tarefa de id 1 não encontrada.");
        assertEquals(exception.getStatus(), HttpStatus.NOT_FOUND);
    }

    @Test
    void shouldListWithSucess() {
        Task model = from(Task.class).gimme(TaskFixture.MODEL);
        Task model1 = from(Task.class).gimme(TaskFixture.MODEL);
        Task model2 = from(Task.class).gimme(TaskFixture.MODEL);
        List<Task> result = new ArrayList<>();
        result.add(model);
        result.add(model1);
        result.add(model2);

        Pageable pageable = Pageable.ofSize(10);
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(result));
        Page<TaskResponse> response = service.list(pageable);
        Assertions.assertFalse(response.isEmpty());
    }

    @Test
    void shouldListEmpty() {
        List<Task> result = new ArrayList<>();

        Pageable pageable = Pageable.ofSize(10);
        when(repository.findAll(pageable)).thenReturn(new PageImpl<>(result));
        Page<TaskResponse> response = service.list(pageable);
        Assertions.assertTrue(response.isEmpty());
    }

}
