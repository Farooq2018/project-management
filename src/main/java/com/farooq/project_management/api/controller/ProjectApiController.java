package com.farooq.project_management.api.controller;

import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.ProjectRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app-api/projects")
public class ProjectApiController {

    @Autowired
    ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getProjects(){
        return projectRepository.findAll();
    }

    @GetMapping("/{id}")
    public Project getProjectById(@PathVariable("id") Long id){
        return projectRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Project create(@RequestBody @Valid Project project) {
        return projectRepository.save(project);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Project update(@RequestBody @Valid Project project) {
        return projectRepository.save(project);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Project partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Project patchProject) {
        Project project = projectRepository.findById(id).get();

        if (patchProject.getName() != null) {
            project.setName(patchProject.getName());
        }
        if (patchProject.getStage() != null) {
            project.setStage(patchProject.getStage());
        }
        if (patchProject.getDescription() != null) {
            project.setDescription(patchProject.getDescription());
        }

        return projectRepository.save(project);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            projectRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
