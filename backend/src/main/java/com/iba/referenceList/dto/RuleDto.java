package com.iba.referenceList.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;

@Setter
@Getter
public class RuleDto {

    private Long id;

    private String name;

    private String text;

    private Instant createDate;

    private List<Long> referenceNoteIds;

}
