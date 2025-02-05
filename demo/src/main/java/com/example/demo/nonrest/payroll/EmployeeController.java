package com.example.demo.nonrest.payroll;

/***
 * Spring Rest Controller Tutorial
 * https://spring.io/guides/tutorials/rest
 *
 * Practiced by Ashish Patel
 * Date: Feb 4, 2025
 */

import org.springframework.web.bind.annotation.*;
import java.util.List;



@RestController
class EmployeeController {

    private final EmployeeRepository repository;

    EmployeeController(EmployeeRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/employees")
    List<Employee> getEmployees() {
        return repository.findAll();
    }

    @PostMapping("/employees")
    Employee createEmployee(@RequestBody Employee employee) {
        return repository.save(employee);
    }

    // Single item

    @GetMapping("/employees/{id}")
    Employee one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable long id) {

        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> repository.save(newEmployee)
                );
    }

    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable long id) {
        repository.deleteById(id);
    }
}
