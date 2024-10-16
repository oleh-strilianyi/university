package interview.test.task.university.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import interview.test.task.university.entity.Lector;

public class CommandProcessor {

    private final Map<String, Consumer<String>> commandMap = new LinkedHashMap<>();
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    public CommandProcessor(DepartmentService departmentService, LectorService lectorService) {
        this.departmentService = departmentService;
        this.lectorService = lectorService;
        registerCommands();
    }

    private void registerCommands() {
        commandMap.put("Who is head of department", (input) -> {
            String departmentName = input.substring("Who is head of department".length()).trim();
            String result = departmentService.getHeadOfDepartment(departmentName);
            System.out.println("Answer: Head of " + departmentName + " department is " + result);
        });

        commandMap.put("Show the average salary for the department", (input) -> {
            String departmentName = input.substring("Show the average salary for the department".length()).trim();
            String result = departmentService.getAverageSalary(departmentName);
            System.out.println("Answer: " + result);
        });

        commandMap.put("Show count of employee for", (input) -> {
            String departmentName = input.substring("Show count of employee for".length()).trim();
            String result = departmentService.getEmployeeCount(departmentName);
            System.out.println("Answer: " + result);
        });

        commandMap.put("Global search by", (input) -> {
            String template = input.substring("Global search by".length()).trim();
            String result = lectorService.globalSearch(template).stream()
                    .map(Lector::getName)
                    .collect(Collectors.joining(", "));
            System.out.println("Answer: " + result);
        });

        commandMap.put("Show", (input) -> {
            if (input.endsWith("statistics")) {
                String departmentName = input.substring("Show".length(), input.indexOf("statistics")).trim();
                String result = departmentService.getDepartmentStatistics(departmentName);
                System.out.println("Answer:\n" + result);
            }
        });
    }

    public void processInput(String input) {
        for (String command : commandMap.keySet()) {
            if (input.startsWith(command)) {
                commandMap.get(command).accept(input);
                return;
            }
        }
        System.out.println("Invalid command");
    }
}
