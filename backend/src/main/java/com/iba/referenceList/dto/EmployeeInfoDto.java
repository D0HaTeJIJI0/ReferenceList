package com.iba.referenceList.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class EmployeeInfoDto {

    private UUID id;

    private String name;

    private Integer age;

    private Boolean married;

    private String boss;

    private List<String> subordinators;

}
