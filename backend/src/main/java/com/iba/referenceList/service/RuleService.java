package com.iba.referenceList.service;

import com.iba.referenceList.dto.PageRuleDto;
import com.iba.referenceList.dto.RuleDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RuleService {

    void deleteRules(List<RuleDto> ruleDtos);

    List<RuleDto> getAllRules();

    PageRuleDto getAllRules(Pageable pageable);
}
