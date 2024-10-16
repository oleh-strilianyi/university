package interview.test.task.university.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "lectors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lector {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double salary;

    @Enumerated(EnumType.STRING)
    private Degree degree;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Department> departments;

}
