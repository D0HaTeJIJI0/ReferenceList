package com.iba.referenceList.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PageRuleDto {

    private List<RuleDto> ruleDtos;

    private Integer currentPage;

    private Integer totalPageNumber;

}
