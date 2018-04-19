package templatemicroservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TemplateDto {
    @JsonProperty
    private int id;
    @JsonProperty
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
