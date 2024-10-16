package interview.test.task.university.repository;

import interview.test.task.university.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN FETCH d.lectors WHERE LOWER(d.name) = LOWER(:name)")
    Optional<Department> findByNameIgnoreCase(String name);
}
