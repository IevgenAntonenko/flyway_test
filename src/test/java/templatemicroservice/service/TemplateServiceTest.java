package templatemicroservice.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import templatemicroservice.domain.entity.Template;
import templatemicroservice.domain.repository.TemplateRepository;
import templatemicroservice.dto.TemplateDto;
import templatemicroservice.service.impl.TemplateServiceImpl;

@RunWith(SpringRunner.class)
public class TemplateServiceTest {

    private static final int ID = 1;
    @InjectMocks
    private TemplateServiceImpl service;

    @Mock
    private TemplateRepository repository;


    @Test
    public void getOne() {
        Template template = new Template();
        template.setId(ID);
        when(repository.getOne(eq(ID))).thenReturn(template);
        TemplateDto result = service.getTemplate(ID);
    }


    @Test
    public void findAll() {
        Template template = new Template();
        template.setId(ID);
        when(repository.findAll()).thenReturn(Collections.singletonList(template));
        List<TemplateDto> result = service.findAll();
        assertThat(result, hasSize(1));
    }

    @Test
    public void saveOne() {
        TemplateDto dto = new TemplateDto();
        dto.setId(ID);
        dto.setDescription("description");
        Template template = new Template();
        template.setId(1);
        when(repository.save(any(Template.class))).thenReturn(template);

        service.createTemplate(dto);
        verify(repository, atLeastOnce()).save(any(Template.class));
    }

    @Test
    public void updateTemplateDescription() {

        String newDesc = "New description";
        Template template = new Template();
        template.setId(ID);
        when(repository.getOne(eq(ID))).thenReturn(template);
        when(repository.save(any(Template.class))).then(returnsFirstArg());

        TemplateDto result = service.updateTemplateDescription(ID, newDesc);

        verify(repository, atLeastOnce()).save(any(Template.class));
        assertThat(newDesc, is(result.getDescription()));
    }

    @Test
    public void delete() {
        service.deleteTemplate(ID);
        verify(repository, atLeastOnce()).delete(eq(ID));
    }


}
