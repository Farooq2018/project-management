package com.farooq.project_management.service;

import com.farooq.project_management.dto.ChartData;
import com.farooq.project_management.dto.TimeChartData;
import com.farooq.project_management.entity.Project;
import com.farooq.project_management.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    ProjectRepository projectRepository;

    public Project save(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAll() {
        return projectRepository.findAll();
    }

    public List<ChartData> getProjectStatus() {
        return projectRepository.getProjectStatus();
    }

    public List<TimeChartData> getTimeData() {
        return projectRepository.getTimeData();
    }
    public Project findByProjectId(Long id) {
        return projectRepository.findByProjectId(id);
    }

    public void delete(Project theProject) {
        projectRepository.delete(theProject);
    }
}
