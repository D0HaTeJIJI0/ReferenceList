package com.iba.referenceList.service;

import com.iba.referenceList.dto.EmployeeDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    void deleteEmployees(List<EmployeeDto> employeeDtos);

    List<EmployeeDto> getAllSubordinatesOfEmployee(EmployeeDto employeeDto);

    List<EmployeeDto> getAllEmployees();

    List<EmployeeDto> getAllEmployees(Pageable pageable);

}
