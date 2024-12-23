package com.farooq.project_management.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employees")
public class EmployeeController {


    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping("/new")
    public String displayEmployeeForm(Model model) {

        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "new-employee";
    }

    @PostMapping("/save")
    public String createEmployee(Model model, Employee employee) {
        //save to the database using an employee CRUD repo
        employeeRepository.save(employee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employees/new";
    }
}
