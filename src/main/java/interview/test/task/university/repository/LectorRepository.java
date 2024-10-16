package interview.test.task.university.repository;

import interview.test.task.university.entity.Lector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    List<Lector> findByNameContainingIgnoreCase(String template);

}
