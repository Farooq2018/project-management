package com.farooq.project_management.controller;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String displayEmployees(Model model) {
        List<Employee> employees = employeeService.getAll();

        model.addAttribute("employees", employees);

        return "employees/list-employees";
    }

    @GetMapping("/new")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayEmployeeForm(Model model) {

        Employee anEmployee = new Employee();
        model.addAttribute("employee", anEmployee);
        return "employees/new-employee";
    }

    @PostMapping("/save")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String createEmployee(Model model, @Valid Employee employee, Errors errors) {

        if (errors.hasErrors())
            return "employees/new-employee";
        //save to the database using an employee CRUD repo
        employeeService.save(employee);

        // Use a redirect to prevent duplicate submissions
        return "redirect:/employees";
    }

    @GetMapping("/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String displayEmployeeUpdateForm(@RequestParam("id") Long id, Model model) {
        Employee theEmployee = employeeService.findByEmployeeId(id);
        model.addAttribute("employee", theEmployee);
        return "employees/new-employee";
    }

    @GetMapping("/delete")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteEmployee(@RequestParam("id") Long id) {
        Employee theEmployee = employeeService.findByEmployeeId(id);
        employeeService.delete(theEmployee);
        return "redirect:/employees";
    }
}
