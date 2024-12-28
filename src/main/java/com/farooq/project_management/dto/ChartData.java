package com.farooq.project_management.dto;

import org.springframework.stereotype.Service;

@Service
public interface ChartData {

    public String getLabel();
    public Long getCount();
}
