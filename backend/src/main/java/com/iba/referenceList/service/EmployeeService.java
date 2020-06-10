package com.iba.referenceList.service;

import com.iba.referenceList.dto.EmployeeDto;

import java.util.List;
import java.util.UUID;

public interface EmployeeService {

    void deleteEmployees(List<EmployeeDto> employeeDtos);

    List<EmployeeDto> getAllSubordinatesOfEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

}
