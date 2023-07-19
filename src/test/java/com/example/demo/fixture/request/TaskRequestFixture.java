package com.example.demo.fixture.request;

import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;
import com.example.demo.dto.request.TaskRequest;
import com.example.demo.model.Task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static br.com.six2six.fixturefactory.Fixture.of;

public class TaskRequestFixture implements TemplateLoader {

    public static final String REQUEST = "REQUEST";

    @Override
    public void load() {
        of(TaskRequest.class).addTemplate(REQUEST, new Rule() {
            {
                add("title", name());
                add("description", name());
                add("term", LocalDate.now().plus(5, ChronoUnit.DAYS));
                add("duration", random(Integer.class, range(1, 25)));
                add("departamentId", 1L);
            }
        });
    }

}
