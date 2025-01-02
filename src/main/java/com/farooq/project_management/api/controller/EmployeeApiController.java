package com.farooq.project_management.api.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.repository.EmployeeRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {

    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id){
        return employeeRepository.findById(id).get();
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Employee create(@RequestBody @Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @PutMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public Employee update(@RequestBody @Valid Employee employee) {
        return employeeRepository.save(employee);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public Employee partialUpdate(@PathVariable("id") Long id, @RequestBody @Valid Employee patchEmployee) {
        Employee employee = employeeRepository.findById(id).get();

        if (patchEmployee.getEmail() != null) {
            employee.setEmail(patchEmployee.getEmail());
        }
        if (patchEmployee.getFirstName() != null) {
            employee.setFirstName(patchEmployee.getFirstName());
        }
        if (patchEmployee.getLastName() != null) {
            employee.setLastName(patchEmployee.getLastName());
        }

        return employeeRepository.save(employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        try {
            employeeRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(params = {"page", "size"})
    @ResponseStatus(HttpStatus.OK)
    public Page<Employee> findPaginatedEmployees(@RequestParam("page") int page, @RequestParam("size") int size) {
        Pageable pageAndSize = PageRequest.of(page, size);
        return employeeRepository.findAll(pageAndSize);
    }
}
