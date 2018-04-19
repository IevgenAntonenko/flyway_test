package templatemicroservice.repository;

import templatemicroservice.domain.entity.Template;
import templatemicroservice.domain.repository.TemplateRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles("testdb")
@RunWith(SpringRunner.class)
@DataJpaTest
public class RepositoryTest {

    @Autowired
    private TemplateRepository templateRepository;

    @Test
    public void testCreate() {
        Template template = new Template();

        template.setTestColumn("test column");
        templateRepository.saveAndFlush(template);

        System.out.println("hello");
    }
}
