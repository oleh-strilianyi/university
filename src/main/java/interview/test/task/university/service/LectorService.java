package interview.test.task.university.service;

import interview.test.task.university.entity.Lector;
import interview.test.task.university.repository.LectorRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LectorService {

    private final LectorRepository lectorRepository;

    public LectorService(LectorRepository lectorRepository) {
        this.lectorRepository = lectorRepository;
    }

    public List<Lector> globalSearch(String template) {
        return lectorRepository.findByNameContainingIgnoreCase(template);
    }

    @Transactional
    public List<Lector> findAllLectors() {
        return lectorRepository.findAll();
    }

    @Transactional
    public Lector getLectorWithDepartments(Long id) {
        return lectorRepository.findById(id).orElseThrow();
    }
}
