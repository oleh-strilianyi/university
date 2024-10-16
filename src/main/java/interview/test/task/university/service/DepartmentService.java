package interview.test.task.university.service;

import interview.test.task.university.entity.Degree;
import interview.test.task.university.entity.Department;
import interview.test.task.university.entity.Lector;
import interview.test.task.university.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Optional<Department> findByName(String name) {
        return departmentRepository.findByNameIgnoreCase(name);
    }

    public String getHeadOfDepartment(String departmentName) {
        Optional<Department> departmentOpt = findByName(departmentName);
        if (departmentOpt.isPresent()) {
            Lector head = departmentOpt.get().getHead();
            return head != null ? head.getName() : "No head assigned";
        } else {
            return "Department not found";
        }
    }

    @Transactional
    public String getDepartmentStatistics(String departmentName) {
        Optional<Department> departmentOpt = findByName(departmentName);
        if (departmentOpt.isPresent()) {
            Set<Lector> lectors = departmentOpt.get().getLectors();

            long assistants = lectors.stream().filter(l -> l.getDegree() == Degree.ASSISTANT).count();
            long associateProfessors = lectors.stream().filter(l -> l.getDegree() == Degree.ASSOCIATE_PROFESSOR).count();
            long professors = lectors.stream().filter(l -> l.getDegree() == Degree.PROFESSOR).count();

            return String.format("assistants - %d\nassociate professors - %d\nprofessors - %d",
                    assistants, associateProfessors, professors);
        } else {
            return "Department not found";
        }
    }

    public String getAverageSalary(String departmentName) {
        Optional<Department> departmentOpt = findByName(departmentName);
        if (departmentOpt.isPresent()) {
            Set<Lector> lectors = departmentOpt.get().getLectors();
            double averageSalary = lectors.stream().mapToDouble(Lector::getSalary).average().orElse(0.0);
            return String.format("The average salary of %s is %.2f", departmentName, averageSalary);
        } else {
            return "Department not found";
        }
    }

    public String getEmployeeCount(String departmentName) {
        Optional<Department> departmentOpt = findByName(departmentName);
        if (departmentOpt.isPresent()) {
            int count = departmentOpt.get().getLectors().size();
            return String.format("%d", count);
        } else {
            return "Department not found";
        }
    }
}
