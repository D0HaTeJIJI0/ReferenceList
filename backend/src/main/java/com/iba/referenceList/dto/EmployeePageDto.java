package com.iba.referenceList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class EmployeePageDto {

    private List<EmployeeDto> employeeDtos;

    private Integer currentPage;

    private Integer totalPageNumber;

}
