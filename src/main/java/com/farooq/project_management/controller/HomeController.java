package com.farooq.project_management.controller;

import com.farooq.project_management.dto.ChartData;
import com.farooq.project_management.dto.EmployeeProject;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.EmployeeRepository;
import com.farooq.project_management.repository.ProjectRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping("/")
    public String displayHome(Model model) throws JsonProcessingException {

        Map<String, Object> map = new HashMap<>();
        //Querying the database for project
        List<Project> projects = projectRepository.findAll();
        model.addAttribute("projectsList", projects);

        //Get Project Data
        List<ChartData> projectData = projectRepository.getProjectStatus();

        //Convert projectData object into json structure for use in Javascript
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonString = objectMapper.writeValueAsString(projectData);
        model.addAttribute("projectStatusCount", jsonString);

        //Querying the database for employees
        List<EmployeeProject> employeesProjectCount = employeeRepository.getEmployeeProjects();
        model.addAttribute("employeesProjectCount", employeesProjectCount);

        return "main/home";
    }
}
