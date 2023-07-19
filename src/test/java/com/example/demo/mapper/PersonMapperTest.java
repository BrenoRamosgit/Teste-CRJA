package com.example.demo.mapper;

import com.example.demo.BaseTest;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.fixture.model.DepartamentFixture;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.fixture.request.PersonRequestFixture;
import com.example.demo.fixture.request.TaskRequestFixture;
import com.example.demo.model.Departament;
import com.example.demo.model.Person;
import com.example.demo.model.Task;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static br.com.six2six.fixturefactory.Fixture.from;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@SpringBootTest
public class PersonMapperTest extends BaseTest {



    @InjectMocks
    private PersonMapper personMapper;

    @MockBean
    private DepartamentRepository departamentRepository;

    @Test
    public void shouldMapperModelToResponse() throws Exception {
        Person model = from(Person.class).gimme(PersonFixture.MODEL);

        PersonResponse response = personMapper.response(model);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getName(), response.getName());
    }

    @Test
    public void shouldMapperListModelToResponse() throws Exception {
        Person model = from(Person.class).gimme(PersonFixture.MODEL);
        List<Person> models = singletonList(model);

        List<PersonResponse> responses = personMapper.response(models);
        PersonResponse response = responses.get(0);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getName(), response.getName());
    }

    @Test
    public void shouldMapperRequestToModel() throws Exception {
        PersonRequest request = from(PersonRequest.class).gimme(PersonRequestFixture.REQUEST);

        Departament departament = from(Departament.class).gimme(DepartamentFixture.MODEL);
        when(departamentRepository.findById(1L)).thenReturn(Optional.ofNullable(departament));

        Person model = personMapper.create(request);

        assertNotNull(model);
        assertEquals(model.getName(), request.getName());
        assertNotNull(model.getDepartament());
    }

}
