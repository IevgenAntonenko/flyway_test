package templatemicroservice.service;

import java.util.List;

import templatemicroservice.dto.TemplateDto;

public interface TemplateService {
    TemplateDto getTemplate(Integer id);

    List<TemplateDto> findAll();

    void createTemplate(TemplateDto dto);

    void deleteTemplate(Integer id);

    TemplateDto updateTemplateDescription(Integer id, String description);
}
