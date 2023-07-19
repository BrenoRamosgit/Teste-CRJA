package com.example.demo.mapper;

import com.example.demo.BaseTest;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.dto.response.TaskResponse;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.fixture.model.TaskFixture;
import com.example.demo.fixture.request.PersonRequestFixture;
import com.example.demo.fixture.request.TaskRequestFixture;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskMapperTest extends BaseTest {


    private Class<TaskMapper> clazz = TaskMapper.class;

    @Test
    public void testClassAnnotations() throws Exception {
        assertTrue(clazz.isAnnotationPresent(Component.class));
    }

    @InjectMocks
    private TaskMapper taskMapper;

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
}
