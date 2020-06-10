package com.iba.referenceList.controller;

import com.iba.referenceList.dto.RuleDto;
import com.iba.referenceList.dto.RuleDto;
import com.iba.referenceList.service.RuleService;
import com.iba.referenceList.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.iba.referenceList.route.WebRouteConstants.*;

@RestController
@RequestMapping(API)
public class RuleController {

    @Autowired
    private RuleService ruleService;

    @DeleteMapping(DELETE_RULES)
    public ResponseEntity<?> deleteRules(
            @RequestBody List<RuleDto> ruleDtos) {

        ruleService.deleteRules(ruleDtos);

        return ResponseEntity.noContent()
                .build();
    }

    @GetMapping(GET_ALL_RULES)
    public ResponseEntity<?> getAllRules(
            @PageableDefault(sort = { "name" }, size = 1, direction = Sort.Direction.ASC) Pageable pageable
    ) {
        List<RuleDto> allRules = ruleService.getAllRules(pageable);

        return ResponseEntity.ok()
                .body(allRules);
    }

}
