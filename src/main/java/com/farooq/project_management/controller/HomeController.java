package com.farooq.project_management.controller;

import com.farooq.project_management.dto.ChartData;
import com.farooq.project_management.dto.EmployeeProject;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.service.EmployeeService;
import com.farooq.project_management.service.ProjectService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Value("${version}")
    private String version;

    @Autowired
    ProjectService projectService;

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/")
    public String displayHome(Model model, Principal principal) throws JsonProcessingException {

        model.addAttribute("envVersion", version);

        Map<String, Object> map = new HashMap<>();
        //Querying the database for project
        List<Project> projects = projectService.getAll();
        model.addAttribute("projectsList", projects);

        //Get Project Data
        List<ChartData> projectData = projectService.getProjectStatus();

        //Convert projectData object into json structure for use in Javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCount", jsonString);

        //Querying the database for employees
        List<EmployeeProject> employeesProjectCount = employeeService.getEmployeeProjects();
        model.addAttribute("employeesProjectCount", employeesProjectCount);

        //Retrieved User Login Info
        if (principal != null) {
            model.addAttribute("username", principal.getName()); // Retrieves logged-in user's username
        } else {
            model.addAttribute("username", "Guest"); // Default value for non-logged-in users
        }

        return "main/home";
    }
}
