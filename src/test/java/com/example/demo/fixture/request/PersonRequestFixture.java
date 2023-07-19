package com.example.demo.fixture.request;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.demo.dto.request.PersonRequest;
import com.example.demo.model.Person;

import static br.com.six2six.fixturefactory.Fixture.of;

public class PersonRequestFixture implements TemplateLoader {


    public static final String REQUEST = "Request";

    @Override
    public void load() {
        of(PersonRequest.class).addTemplate(REQUEST, new Rule() {
            {
                add("name", name());
                add("departamentId", 1L);
            }
        });
    }
}
