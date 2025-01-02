package com.farooq.project_management.repository;

import com.farooq.project_management.dto.EmployeeProject;
import com.farooq.project_management.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "apiemployees", path = "apiemployees")
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(nativeQuery = true,
           value = "SELECT e.first_name AS firstName, e.last_name AS lastName, COUNT(pe.employee_id) AS projectCount " +
                   "FROM employee e " +
                   "LEFT JOIN project_employee pe " +
                   "ON e.employee_id = pe.employee_id " +
                   "GROUP BY e.first_name, e.last_name " +
                   "ORDER BY 3 DESC")
    public List<EmployeeProject> getEmployeeProjects();

    public Employee findByEmail(String value);

    public Employee findByEmployeeId(Long id);
}
