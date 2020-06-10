package com.iba.referenceList.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {

    private UUID id;

    private String name;

    private Integer age;

    private Boolean married;

    private UUID bossId;

    private List<UUID> subordinateIds;

    private List<Long> referenceNoteIds;

    private Boolean selected = false;

    public EmployeeDto(UUID id) {
        this.id = id;
    }

}
