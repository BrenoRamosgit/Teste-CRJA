package com.example.demo.fixture.model;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.demo.model.Departament;
import com.example.demo.model.Person;

import static br.com.six2six.fixturefactory.Fixture.of;

public class DepartamentFixture implements TemplateLoader {

    public static final String MODEL = "MODEL";

    @Override
    public void load() {
        of(Departament.class).addTemplate(MODEL, new Rule() {
            {
                add("id", random(Long.class, range(1l, 100l)));
                add("name", name());
            }
        });
    }

}
