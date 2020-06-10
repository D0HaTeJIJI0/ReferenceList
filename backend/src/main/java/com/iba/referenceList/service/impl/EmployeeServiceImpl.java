package com.iba.referenceList.service.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.dto.EmployeeDto;
import com.iba.referenceList.repository.EmployeeRepository;
import com.iba.referenceList.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    @Qualifier(value = "EmployeeConverter")
    private BaseConverter<Employee, EmployeeDto> converter;

    @Override
    public void deleteEmployees(List<EmployeeDto> employeeDtos) {
        List<Employee> employees = converter.toEntity(employeeDtos);
        repository.deleteAll(employees);
    }

    @Override
    public List<EmployeeDto> getAllSubordinatesOfEmployee(EmployeeDto employeeDto) {
        Employee employee = converter.toEntity(employeeDto);
        List<Employee> allByBoss = repository.findAllByBoss(employee);
        return converter.toDto(allByBoss);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> all = repository.findAll();
        return converter.toDto(all);
    }

    @Override
    public List<EmployeeDto> getAllEmployees(Pageable pageable) {
        Page<Employee> all = repository.findAll(pageable);
        List<Employee> content = all.getContent();
        return converter.toDto(content);
    }
}
