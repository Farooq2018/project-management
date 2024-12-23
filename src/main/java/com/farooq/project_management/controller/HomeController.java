package com.farooq.project_management.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.EmployeeRepository;
import com.farooq.project_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String displayHome(Model model) {

        //Querying the database for project
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projectsList", projects);


        //Querying the database for employees
        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("employeesList", employees);

        return "main/home";
    }
}
