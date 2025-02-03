package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.EmployeeRepository;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.entity.Department;
import com.example.demo.entity.Employee;
import com.example.demo.feign.DepartmentFeignClient;


@Service
public class Employeeservice {

    @Autowired
    private EmployeeRepository employeeRepo;
    //private DepartmentRepository departmentRepo;

    @Autowired
    private DepartmentFeignClient departmentFeignClient; // Feign client to fetch department details

    // Retrieve all employees
    public List<Employee> getAllEmployees() {
        List<Employee> employees = employeeRepo.findAll();
        for (Employee employee : employees) {
            // Fetch the department using the Feign client
            Department department = departmentFeignClient.getDepartmentById(employee.getDepartmentId());
            //employee.setDepartmentName(department.getDepartmentName()); // Set department name
        }
        return employees;
    }

    // Retrieve an employee by ID
    public Employee getEmployeeById(int id) {
        Employee employee = employeeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        // Fetch the department using the Feign client
        Department department = departmentFeignClient.getDepartmentById(employee.getDepartmentId());
       // employee.setDepartmentName(department.getDepartmentName()); // Set department name
        return employee;
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
            existingEmployee.setPosition(employeeDetails.getPosition());
            existingEmployee.setDepartmentId(employeeDetails.getDepartmentId());
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
