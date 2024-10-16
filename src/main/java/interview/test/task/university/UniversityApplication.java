package interview.test.task.university;

import interview.test.task.university.service.CommandProcessor;
import interview.test.task.university.service.DepartmentService;
import interview.test.task.university.service.LectorService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.util.Scanner;

@SpringBootApplication
@Profile("!test")
public class UniversityApplication {

	private final DepartmentService departmentService;
	private final LectorService lectorService;

	public UniversityApplication(DepartmentService departmentService, LectorService lectorService) {
		this.departmentService = departmentService;
		this.lectorService = lectorService;
	}

	public static void main(String[] args) {
		SpringApplication.run(UniversityApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			CommandProcessor processor = new CommandProcessor(departmentService, lectorService);

			while (true) {
				System.out.print("User Input: ");
				String input = scanner.nextLine();

				if (input.equalsIgnoreCase("exit")) {
					break;
				}

				processor.processInput(input);
			}
		};
	}
}
