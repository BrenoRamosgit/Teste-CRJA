package com.example.demo.mapper;

import com.example.demo.BaseTest;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.fixture.request.PersonRequestFixture;
import com.example.demo.model.Person;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PersonMapperTest extends BaseTest {

    private Class<PersonMapper> clazz = PersonMapper.class;

    @Test
    public void testClassAnnotations() throws Exception {
        assertTrue(clazz.isAnnotationPresent(Component.class));
    }

    @InjectMocks
    private PersonMapper personMapper;

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

}
