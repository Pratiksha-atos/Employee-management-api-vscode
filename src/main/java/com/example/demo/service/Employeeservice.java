package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.entity.Employee;

@Service
public class Employeeservice {

    @Autowired
    private EmployeeRepository employeeRepo;

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        return employeeRepo.findAll();
    }

    // Retrieve an employee by ID
    public Employee getEmployeeById(int id) {
        return employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    // Add a new employee
    public Employee addEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }

    // Update an existing employee
    public Employee updateEmployee(int id, Employee employeeDetails) {
        return employeeRepo.findById(id).map(existingEmployee -> {
            existingEmployee.setName(employeeDetails.getName());
            existingEmployee.setEmail(employeeDetails.getEmail());
            existingEmployee.setDepartment(employeeDetails.getDepartment());
            existingEmployee.setPosition(employeeDetails.getPosition());
            return employeeRepo.save(existingEmployee);
        }).orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));
    }

    // Delete an employee
    public String deleteEmployee(int id) {
        if (employeeRepo.existsById(id)) {
            employeeRepo.deleteById(id);
            return "Employee deleted successfully.";
        } else {
            return "Employee not found.";
        }
    }
}
