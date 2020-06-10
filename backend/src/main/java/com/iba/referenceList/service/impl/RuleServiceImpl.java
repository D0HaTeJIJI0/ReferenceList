package com.iba.referenceList.service.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Rule;
import com.iba.referenceList.dto.RuleDto;
import com.iba.referenceList.repository.RuleRepository;
import com.iba.referenceList.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleServiceImpl implements RuleService {

    @Autowired
    private RuleRepository repository;

    @Autowired
    @Qualifier(value = "RuleConverter")
    private BaseConverter<Rule, RuleDto> converter;

    @Override
    public void deleteRules(List<RuleDto> ruleDtos) {
        List<Rule> rules = converter.toEntity(ruleDtos);
        repository.deleteAll(rules);
    }

    @Override
    public List<RuleDto> getAllRules() {
        List<Rule> all = repository.findAll();
        return converter.toDto(all);
    }

    @Override
    public List<RuleDto> getAllRules(Pageable pageable) {
        List<Rule> all = repository.findAll(pageable).getContent();
        return converter.toDto(all);
    }
}
