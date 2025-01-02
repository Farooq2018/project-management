package com.farooq.project_management.repository;

import com.farooq.project_management.dto.ChartData;
import com.farooq.project_management.dto.TimeChartData;
import com.farooq.project_management.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(nativeQuery = true,
            value = "SELECT stage AS label, COUNT(*) AS count " +
                    "FROM PROJECT " +
                    "GROUP BY stage")
    public List<ChartData> getProjectStatus();

    @Query(nativeQuery = true,
            value = "SELECT name AS projectName, start_date AS startDate, end_date AS endDate " +
                    "FROM project " +
                    "WHERE start_date IS NOT NULL")
    public List<TimeChartData> getTimeData();

    public Project findByProjectId(Long id);
}
