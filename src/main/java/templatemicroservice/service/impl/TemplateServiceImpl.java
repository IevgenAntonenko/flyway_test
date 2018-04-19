package templatemicroservice.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import templatemicroservice.domain.entity.Template;
import templatemicroservice.domain.repository.TemplateRepository;
import templatemicroservice.dto.TemplateDto;
import templatemicroservice.service.TemplateService;

@Service
public class TemplateServiceImpl implements TemplateService {

    @Autowired
    private TemplateRepository repository;

    @Override
    public TemplateDto getTemplate(Integer id) {
        return Optional.ofNullable(repository.getOne(id)).map(this::toDto).orElse(new TemplateDto());
    }

    @Override
    public List<TemplateDto> findAll() {
        return repository.findAll().stream().map(this::toDto).collect(toList());
    }

    @Override
    public void createTemplate(TemplateDto dto) {
        repository.save(toEntity(dto));
    }

    private TemplateDto toDto(Template template) {
        TemplateDto dto = new TemplateDto();
        dto.setId(template.getId());
        return dto;
    }

    private Template toEntity(TemplateDto dto) {
        Template template = new Template();
        template.setId(dto.getId());
        return template;
    }

    @Override
    public void deleteTemplate(Integer id) {
        repository.delete(id);
    }

    @Override
    public TemplateDto updateTemplateDescription(Integer id, String description) {
        Template template = repository.getOne(id);
        return toDto(repository.save(template));
    }
}
