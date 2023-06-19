package nl.miwgroningen.ch11.vincent.educationmanager.repository;

import nl.miwgroningen.ch11.vincent.educationmanager.model.EducationalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationalActivityRepository extends JpaRepository<EducationalActivity, Long> {
    List<EducationalActivity> findAllBySuperActivityIsNull();
}
