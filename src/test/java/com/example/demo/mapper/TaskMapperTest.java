package com.example.demo.mapper;

import com.example.demo.BaseTest;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.fixture.model.DepartamentFixture;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.fixture.model.TaskFixture;
import com.example.demo.fixture.request.PersonRequestFixture;
import com.example.demo.fixture.request.TaskRequestFixture;
import com.example.demo.model.Departament;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.DepartamentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static br.com.six2six.fixturefactory.Fixture.from;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TaskMapperTest extends BaseTest {

    @InjectMocks
    private TaskMapper taskMapper;

    @MockBean
    private DepartamentRepository departamentRepository;

    private Class<TaskMapper> clazz = TaskMapper.class;

    @Test
    public void testClassAnnotations() throws Exception {
        assertTrue(clazz.isAnnotationPresent(Component.class));
    }



    @Test
    public void shouldMapperModelToResponse() throws Exception {
        Task model = from(Task.class).gimme(TaskFixture.MODEL);

        TaskResponse response = taskMapper.response(model);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getTitle(), response.getTitle());
        assertEquals(model.getDescription(), response.getDescription());
        assertEquals(model.getTerm(), response.getTerm());
        assertEquals(model.getDuration(), response.getDuration());
        assertEquals(model.getFinished(), response.getFinished());
    }

    @Test
    public void shouldMapperListModelToResponse() throws Exception {
        Task model = from(Task.class).gimme(TaskFixture.MODEL);
        List<Task> models = singletonList(model);

        List<TaskResponse> responses = taskMapper.response(models);
        TaskResponse response = responses.get(0);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getTitle(), response.getTitle());
        assertEquals(model.getDescription(), response.getDescription());
        assertEquals(model.getTerm(), response.getTerm());
        assertEquals(model.getDuration(), response.getDuration());
        assertEquals(model.getFinished(), response.getFinished());
    }

    @Test
    public void shouldMapperRequestToModel() throws Exception {
        TaskRequest request = from(TaskRequest.class).gimme(TaskRequestFixture.REQUEST);

        Departament departament = from(Departament.class).gimme(DepartamentFixture.MODEL);
        when(departamentRepository.findById(1L)).thenReturn(Optional.ofNullable(departament));

        Task model = taskMapper.create(request);

        assertNotNull(model);
        assertEquals(model.getTitle(), request.getTitle());
        assertEquals(model.getDescription(), request.getDescription());
        assertEquals(model.getTerm(), request.getTerm());
        assertEquals(model.getDuration(), request.getDuration());

    }
}
