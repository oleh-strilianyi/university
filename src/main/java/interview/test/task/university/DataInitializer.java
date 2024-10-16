package interview.test.task.university;

import interview.test.task.university.entity.Degree;
import interview.test.task.university.entity.Department;
import interview.test.task.university.entity.Lector;
import interview.test.task.university.repository.DepartmentRepository;
import interview.test.task.university.repository.LectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Configuration
public class DataInitializer {
    @Bean
    @Transactional
    public CommandLineRunner loadData(LectorRepository lectorRepository, DepartmentRepository departmentRepository) {
        return args -> {
            Lector lector1 = new Lector(null, "Ivan Petrenko", 5000, Degree.ASSISTANT, null);
            Lector lector2 = new Lector(null, "Petro Ivanov", 7000, Degree.ASSOCIATE_PROFESSOR, null);
            Lector lector3 = new Lector(null, "Dmytro Zykov", 9000, Degree.PROFESSOR, null);
            lectorRepository.saveAll(Set.of(lector1, lector2, lector3));

            Department department1 = new Department(null, "Physics", lector3, null);
            Department department2 = new Department(null, "Mathematics", lector2, null);

            department1.setLectors(Set.of(lector1));
            department2.setLectors(Set.of(lector2, lector3));

            departmentRepository.saveAll(Set.of(department1, department2));
        };
    }
}
