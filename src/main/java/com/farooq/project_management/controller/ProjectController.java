package com.farooq.project_management.controller;

import com.farooq.project_management.dto.TimeChartData;
import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.service.EmployeeService;
import com.farooq.project_management.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String displayProjects(Model model) {
        List<Project> projects = projectService.getAll();

        model.addAttribute("projects", projects);

        return "projects/list-projects";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayProjectForm(Model model) {

        Project theProject = new Project();
        model.addAttribute("project", theProject);

        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createProject(@Valid Project project, Model model, Errors errors) {

        if (errors.hasErrors())
            return "/projects/new-project";

        projectService.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects";

    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayProjectUpdateForm(@RequestParam("id") Long id, Model model) {

        Project theProject = projectService.findByProjectId(id);
        model.addAttribute("project", theProject);

        List<Employee> employees = employeeService.getAll();
        model.addAttribute("allEmployees", employees);

        return "projects/new-project";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteProject(@RequestParam("id") Long id) {
        Project theProject = projectService.findByProjectId(id);
        projectService.delete(theProject);
        return "redirect:/projects";
    }

    @GetMapping("/timelines")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayProjectTimeline(Model model) throws JsonProcessingException {

        List<TimeChartData> timeChartData = projectService.getTimeData();
        //Convert projectData object into json structure for use in Javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(timeChartData);
        model.addAttribute("projectTimeList", jsonString);

        return "projects/project-timelines";
    }
}
