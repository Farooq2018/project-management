package com.farooq.project_management.dto;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface TimeChartData {

    public String getProjectName();
    public Date getStartDate();
    public Date getEndDate();
}
