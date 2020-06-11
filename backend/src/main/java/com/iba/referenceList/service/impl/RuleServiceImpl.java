package com.iba.referenceList.service.impl;

import com.iba.referenceList.converter.BaseConverter;
import com.iba.referenceList.domain.Rule;
import com.iba.referenceList.dto.PageRuleDto;
import com.iba.referenceList.dto.RuleDto;
import com.iba.referenceList.repository.RuleRepository;
import com.iba.referenceList.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    public PageRuleDto getAllRules(Pageable pageable) {
        Page<Rule> page = repository.findAll(pageable);
        List<Rule> all = page.getContent();

        return new PageRuleDto(converter.toDto(all),
                page.getNumber(),
                page.getTotalPages());
    }
}
