package com.iba.referenceList.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class EmployeeDto {

    public interface Delete {}

    @NotNull(groups = Delete.class)
    private UUID id;

    private String name;

    private Integer age;

    private Boolean married;

    private UUID bossId;

    private List<UUID> subordinateIds;

    private List<Long> referenceNoteIds;

    public EmployeeDto(UUID id) {
        this.id = id;
    }

}
