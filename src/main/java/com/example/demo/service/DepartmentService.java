package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.feign.DepartmentFeignClient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentFeignClient departmentFeignClient;

    // Fetch all departments using Feign Client
    public List<Department> getAllDepartments() {
        return departmentFeignClient.getAllDepartments();
    }

    // Fetch a single department by ID
    public Department getDepartmentById(int departmentId) {
        return departmentFeignClient.getDepartmentById(departmentId);
    }
}
