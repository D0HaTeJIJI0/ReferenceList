package com.iba.referenceList.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
public class ReferenceNoteDto {

    public interface Add {}

    @Null(groups = Add.class)
    Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    private Instant date;

    @NotNull(groups = Add.class)
    private UUID employeeId;

    @NotNull(groups = Add.class)
    private Long ruleId;

}
