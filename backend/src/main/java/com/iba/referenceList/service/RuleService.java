package com.iba.referenceList.service;

import com.iba.referenceList.dto.RuleDto;

import java.util.List;

public interface RuleService {

    void deleteRules(List<RuleDto> ruleDtos);

    List<RuleDto> getAllRules();

}
