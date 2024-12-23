package com.farooq.project_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.ProjectRepository;

import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping("/new")
    public String displayProjectForm(Model model) {

        Project aProject = new Project();
        model.addAttribute("project", aProject);
        return "new-project";
    }

    @PostMapping("/save")
    public String createProject(Project project, Model model) {

        projectRepository.save(project);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/projects/new";

    }

}
