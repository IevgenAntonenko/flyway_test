package templatemicroservice.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

@Table(name = "TestTable")
@Entity
public class Template {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "testColumn")
    private String testColumn;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestColumn() {
        return testColumn;
    }

    public void setTestColumn(String testColumn) {
        this.testColumn = testColumn;
    }
}
