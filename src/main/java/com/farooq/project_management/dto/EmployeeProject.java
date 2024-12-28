package com.farooq.project_management.dto;

import org.springframework.stereotype.Service;

@Service
public interface EmployeeProject {

    //Need to have the Property names begin with get
    public String getFirstName();
    public String getLastName();
    public String getProjectCount();
}
