package com.farooq.project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.farooq.project_management.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}
