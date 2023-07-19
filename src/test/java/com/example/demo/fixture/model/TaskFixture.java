package com.example.demo.fixture.model;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.demo.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static br.com.six2six.fixturefactory.Fixture.of;

public class TaskFixture implements TemplateLoader {

    public static final String MODEL = "MODEL";

    @Override
    public void load() {
        of(Task.class).addTemplate(MODEL, new Rule() {
            {
                add("id", random(Long.class, range(1l, 100l)));
                add("title", name());
                add("description", name());
                add("term", LocalDate.now().plus(5, ChronoUnit.DAYS));
                add("startTime", LocalDateTime.now().plus(1, ChronoUnit.DAYS));
                add("finishTime", LocalDateTime.now().plus(3, ChronoUnit.DAYS));
                add("duration", random(Integer.class, range(1, 25)));
                add("finished", Boolean.FALSE);
            }
        });
    }

}
