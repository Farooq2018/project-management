package com.farooq.project_management.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.service.EmployeeService;
import com.farooq.project_management.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();

        model.addAttribute("projects", projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        model.addAttribute("project", aProject);

        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String createProject(Project project, Model model) {

        projectService.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects";

    }

}
