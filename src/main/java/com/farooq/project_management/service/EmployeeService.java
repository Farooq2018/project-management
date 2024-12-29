package com.farooq.project_management.service;

import com.farooq.project_management.dto.EmployeeProject;
import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    public List<EmployeeProject> getEmployeeProjects() {
        return employeeRepository.getEmployeeProjects();
    }
}
