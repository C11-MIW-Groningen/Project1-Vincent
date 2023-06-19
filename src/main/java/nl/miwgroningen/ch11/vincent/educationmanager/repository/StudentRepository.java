package nl.miwgroningen.ch11.vincent.educationmanager.repository;

import nl.miwgroningen.ch11.vincent.educationmanager.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
