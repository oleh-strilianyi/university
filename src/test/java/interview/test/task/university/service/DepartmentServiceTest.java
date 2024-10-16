package interview.test.task.university.service;

import interview.test.task.university.entity.Degree;
import interview.test.task.university.entity.Department;
import interview.test.task.university.entity.Lector;
import interview.test.task.university.repository.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetHeadOfDepartment() {
        Lector lector = new Lector(1L, "Dmytro Zykov", 9000, Degree.PROFESSOR, null);
        Department department = new Department(1L, "Computer Science", lector, Set.of());

        when(departmentRepository.findByNameIgnoreCase("Computer Science"))
                .thenReturn(Optional.of(department));

        String result = departmentService.getHeadOfDepartment("Computer Science");
        assertEquals("Dmytro Zykov", result);
    }

    @Test
    void testGetDepartmentStatistics() {
        Lector lector1 = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);
        Lector lector2 = new Lector(2L, "Petro Ivanov", 2000, Degree.ASSOCIATE_PROFESSOR, null);
        Lector lector3 = new Lector(3L, "Dmytro Zykov", 3000, Degree.PROFESSOR, null);

        Department department = new Department(1L, "Mathematics", lector2, Set.of(lector1, lector2, lector3));

        when(departmentRepository.findByNameIgnoreCase("Mathematics"))
                .thenReturn(Optional.of(department));

        String result = departmentService.getDepartmentStatistics("Mathematics");

        assertEquals("assistants - 1\nassociate professors - 1\nprofessors - 1", result);
    }

    @Test
    void testGetAverageSalary() {
        Lector lector1 = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);
        Lector lector2 = new Lector(2L, "Petro Ivanov", 2000, Degree.ASSOCIATE_PROFESSOR, null);
        Department department = new Department(1L, "Mathematics", lector2, Set.of(lector1, lector2));

        when(departmentRepository.findByNameIgnoreCase("Mathematics"))
                .thenReturn(Optional.of(department));

        String result = departmentService.getAverageSalary("Mathematics");

        assertEquals("The average salary of Mathematics is 1500.00", result);
    }

    @Test
    void testGetEmployeeCount() {
        Lector lector1 = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);
        Lector lector2 = new Lector(2L, "Petro Ivanov", 2000, Degree.ASSOCIATE_PROFESSOR, null);
        Department department = new Department(1L, "Mathematics", lector2, Set.of(lector1, lector2));

        when(departmentRepository.findByNameIgnoreCase("Mathematics"))
                .thenReturn(Optional.of(department));

        String result = departmentService.getEmployeeCount("Mathematics");

        assertEquals("2", result);
    }

    @Test
    void testDepartmentNotFound() {
        when(departmentRepository.findByNameIgnoreCase("Nonexistent Department"))
                .thenReturn(Optional.empty());

        String result = departmentService.getHeadOfDepartment("Nonexistent Department");
        assertEquals("Department not found", result);
    }
}
