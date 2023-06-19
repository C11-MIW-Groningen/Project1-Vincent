package nl.miwgroningen.ch11.vincent.educationmanager.repository;

import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
}
