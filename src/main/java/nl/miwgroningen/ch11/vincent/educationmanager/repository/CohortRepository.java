package nl.miwgroningen.ch11.vincent.educationmanager.repository;

import nl.miwgroningen.ch11.vincent.educationmanager.model.Cohort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CohortRepository extends JpaRepository<Cohort, Long> {
    Optional<Cohort> findByName(String name);
}
