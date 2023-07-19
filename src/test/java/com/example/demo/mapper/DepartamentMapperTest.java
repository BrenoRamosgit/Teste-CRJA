package com.example.demo.mapper;

import com.example.demo.BaseTest;
import com.example.demo.dto.response.DepartamentResponse;
import com.example.demo.dto.response.PersonResponse;
import com.example.demo.fixture.model.DepartamentFixture;
import com.example.demo.fixture.model.PersonFixture;
import com.example.demo.model.Departament;
import com.example.demo.model.Person;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.stereotype.Component;

import java.util.List;

import static br.com.six2six.fixturefactory.Fixture.from;
import static java.util.Collections.singletonList;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DepartamentMapperTest extends BaseTest {

    @InjectMocks
    private DepartamentMapper departamentMapper;

    private Class<DepartamentMapper> clazz = DepartamentMapper.class;

    @Test
    public void testClassAnnotations() throws Exception {
        assertTrue(clazz.isAnnotationPresent(Component.class));
    }

    @Test
    public void shouldMapperModelToResponse() throws Exception {
        Departament model = from(Departament.class).gimme(DepartamentFixture.MODEL);

        DepartamentResponse response = departamentMapper.response(model);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getName(), response.getName());
    }

    @Test
    public void shouldMapperListModelToResponse() throws Exception {
        Departament model = from(Departament.class).gimme(DepartamentFixture.MODEL);
        List<Departament> models = singletonList(model);

        List<DepartamentResponse> responses = departamentMapper.response(models);
        DepartamentResponse response = responses.get(0);

        assertNotNull(response);
        assertEquals(model.getId(), response.getId());
        assertEquals(model.getName(), response.getName());
    }
}
