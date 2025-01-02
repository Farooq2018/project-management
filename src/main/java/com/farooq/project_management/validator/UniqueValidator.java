package com.farooq.project_management.validator;

import com.farooq.project_management.entity.Employee;
import com.farooq.project_management.repository.EmployeeRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueValidator implements ConstraintValidator<UniqueValue, String>{

    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        Employee employee = employeeRepository.findByEmail(s);

        return employee == null;
    }
}
