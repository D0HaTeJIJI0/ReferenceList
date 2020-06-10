package com.iba.referenceList.repository;

import com.iba.referenceList.domain.Rule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RuleRepository extends JpaRepository<Rule, Long> {

    Page<Rule> findAll(Pageable pageable);

}
