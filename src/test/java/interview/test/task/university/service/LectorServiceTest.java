package interview.test.task.university.service;

import interview.test.task.university.entity.Degree;
import interview.test.task.university.entity.Lector;
import interview.test.task.university.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class LectorServiceTest {

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorService lectorService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGlobalSearch() {
        Lector lector1 = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);
        Lector lector2 = new Lector(2L, "Petro Ivanov", 2000, Degree.ASSOCIATE_PROFESSOR, null);

        when(lectorRepository.findByNameContainingIgnoreCase("Ivan")).thenReturn(Arrays.asList(lector1, lector2));

        List<Lector> result = lectorService.globalSearch("Ivan");

        assertEquals(2, result.size());
        assertEquals("Ivan Petrenko", result.get(0).getName());
        assertEquals("Petro Ivanov", result.get(1).getName());
    }

    @Test
    void testFindAllLectors() {
        Lector lector1 = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);
        Lector lector2 = new Lector(2L, "Petro Ivanov", 2000, Degree.ASSOCIATE_PROFESSOR, null);

        when(lectorRepository.findAll()).thenReturn(Arrays.asList(lector1, lector2));

        List<Lector> result = lectorService.findAllLectors();

        assertEquals(2, result.size());
        assertEquals("Ivan Petrenko", result.get(0).getName());
        assertEquals("Petro Ivanov", result.get(1).getName());
    }

    @Test
    void testGetLectorWithDepartments() {
        Lector lector = new Lector(1L, "Ivan Petrenko", 1000, Degree.ASSISTANT, null);

        when(lectorRepository.findById(1L)).thenReturn(Optional.of(lector));

        Lector result = lectorService.getLectorWithDepartments(1L);

        assertEquals("Ivan Petrenko", result.getName());
    }

    @Test
    void testGetLectorWithDepartmentsNotFound() {
        when(lectorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> lectorService.getLectorWithDepartments(1L));
    }
}
