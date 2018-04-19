package templatemicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import templatemicroservice.dto.TemplateDto;
import templatemicroservice.service.impl.TemplateServiceImpl;

@RestController
public class TemplateController {

    @Autowired
    private TemplateServiceImpl service;

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<TemplateDto> getOne(@PathVariable Integer id) {
        return new ResponseEntity<>(service.getTemplate(id), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<TemplateDto>> listAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void createOne(@RequestBody TemplateDto input) {
        service.createTemplate(input);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<TemplateDto> updateDescription(@PathVariable Integer id, @RequestBody String description) {
        return new ResponseEntity<>(service.updateTemplateDescription(id, description), HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOne(@PathVariable Integer id) {
        service.deleteTemplate(id);
    }

}
