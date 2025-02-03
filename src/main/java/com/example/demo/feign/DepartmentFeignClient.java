package com.example.demo.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.entity.Department;

import java.util.List;

@FeignClient(name = "department-service") // This name should match the name of your Department microservice in Eureka or application.properties
public interface DepartmentFeignClient {

    @GetMapping("/departments")
    List<Department> getAllDepartments();

    @GetMapping("/departments/{departmentId}")
    Department getDepartmentById(@PathVariable("departmentId") int departmentId);
}
