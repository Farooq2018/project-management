package com.farooq.project_management.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Project {

    @Id
    @SequenceGenerator(name = "project_generator", sequenceName = "project_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "project_generator")
    private Long projectId;

    @NotBlank
    @Size(min = 5, max = 50)
    private String name;

    private String stage;

    @NotBlank
    @Size(min = 10, max = 200)
    private String description;

    @NotNull(message="date cannot be empty")
    @PastOrPresent(message = "Start date must be in the past or present")
    private Date startDate;

    @NotNull(message="date cannot be empty")
    @Future(message = "End date must be in the future")
    private Date endDate;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.PERSIST},
                fetch = FetchType.LAZY)
    @JoinTable(name = "project_employee",
                joinColumns = @JoinColumn(name = "project_id"),
                inverseJoinColumns = @JoinColumn(name = "employee_id"))
    @JsonIgnore
    private List<Employee> employees;

    public Project() {

    }

    public Project(String name, String stage, String description) {
        this.name = name;
        this.stage = stage;
        this.description = description;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
