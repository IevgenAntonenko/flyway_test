package templatemicroservice.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import templatemicroservice.domain.entity.Template;

@Repository
public interface TemplateRepository extends JpaRepository<Template, Integer> {
}
