package com.farooq.project_management.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.EmployeeRepository;
import com.farooq.project_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public String displayProjects(Model model) {
        List<Project> projects = projectRepository.findAll();

        model.addAttribute("projects", projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        model.addAttribute("project", aProject);

        List<Employee> employees = employeeRepository.findAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {

        projectRepository.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects";

    }

}
