package com.example.demo.nonrest.payroll;

public class EmployeeNotFoundException extends RuntimeException {

    EmployeeNotFoundException(Long id) {
        super("Employee with id " + id + " not found");
    }
}
