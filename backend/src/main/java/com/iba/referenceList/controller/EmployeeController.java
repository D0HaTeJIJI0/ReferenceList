package com.iba.referenceList.controller;

import com.iba.referenceList.dto.EmployeeDto;
import com.iba.referenceList.dto.EmployeePageDto;
import com.iba.referenceList.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static com.iba.referenceList.route.WebRouteConstants.*;

@RestController
@RequestMapping(API)
@CrossOrigin
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(POST_NEW_EMPLOYEE)
    public ResponseEntity<?> createEmployee(
            @RequestBody EmployeeDto employeeDto) {

        employeeService.createEmployee(employeeDto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping(DELETE_EMPLOYEES)
    public ResponseEntity<?> deleteEmployees(
            @Validated(EmployeeDto.Delete.class) @RequestBody List<@Valid EmployeeDto> employeeDtos) {

        employeeService.deleteEmployees(employeeDtos);
        List<EmployeeDto> allEmployees = employeeService.getAllEmployees();

        return ResponseEntity.ok(allEmployees);
    }

    @GetMapping(GET_SUBORDINATES)
    public ResponseEntity<?> getSubordinatesForEmployee(
            @PathVariable("id") UUID id) {

        List<EmployeeDto> allSubordinatesOfEmployee = employeeService.getAllSubordinatesOfEmployee(new EmployeeDto(id));

        return ResponseEntity.ok()
                .body(allSubordinatesOfEmployee);
    }


    @GetMapping(GET_ALL_EMPLOYEES)
    public ResponseEntity<?> getAllEmployees(
            @PageableDefault(sort = { "name" }, size = 3, direction = Sort.Direction.ASC)Pageable pageable
            ) {

        EmployeePageDto employeePageDto = employeeService.getAllEmployees(pageable);

        return ResponseEntity.ok()
                .body(employeePageDto);
    }

}
