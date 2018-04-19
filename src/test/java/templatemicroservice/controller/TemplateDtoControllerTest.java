package templatemicroservice.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.nio.charset.Charset;
import java.util.Collections;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import templatemicroservice.dto.TemplateDto;
import templatemicroservice.service.impl.TemplateServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateDtoControllerTest {

    private static final ObjectMapper JACKSON = new ObjectMapper();
    private static final int ID = 2;

    private MockMvc mockMvc;

    private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    @InjectMocks
    private TemplateController controller;

    @Mock
    private TemplateServiceImpl service;

    @Before
    public void setup() throws Exception {
        this.mockMvc = standaloneSetup(controller).build();

    }

    @Test
    public void getOne() throws Exception {
        TemplateDto dto = new TemplateDto();
        dto.setId(ID);
        dto.setDescription("A description");
        when(service.getTemplate(ID)).thenReturn(dto);
        mockMvc.perform(get("/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is("A description")));
    }

    @Test
    public void listAll() throws Exception {
        TemplateDto dto = new TemplateDto();
        dto.setId(ID);
        dto.setDescription("A description");
        when(service.findAll()).thenReturn(Collections.singletonList(dto));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$[0].id", is(ID)))
                .andExpect(jsonPath("$[0].description", is("A description")));
    }

    @Test
    public void updateDescription() throws Exception {
        String description = "New description";
        TemplateDto outDto = new TemplateDto();
        outDto.setId(2);
        outDto.setDescription(description);
        when(service.updateTemplateDescription(eq(ID), eq(description))).thenReturn(outDto);

        mockMvc.perform(put("/" + ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(description))
                .andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$.id", is(ID)))
                .andExpect(jsonPath("$.description", is(description)));
    }


    @Test
    public void postNew() throws Exception {
        String json = JACKSON.writeValueAsString(new TemplateDto());
        mockMvc.perform(post("/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isNoContent());
        verify(service, atLeastOnce()).createTemplate(any());
    }

    @Test
    public void deleteOne() throws Exception {
        mockMvc.perform(delete("/" + ID))
                .andExpect(status().isNoContent());
        verify(service, atLeastOnce()).deleteTemplate(eq(ID));
    }


}
