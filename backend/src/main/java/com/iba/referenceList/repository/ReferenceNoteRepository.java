package com.iba.referenceList.repository;

import com.iba.referenceList.domain.Employee;
import com.iba.referenceList.domain.ReferenceNote;
import com.iba.referenceList.domain.Rule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface ReferenceNoteRepository extends JpaRepository<ReferenceNote, Long> {

    void deleteAllByEmployee(Employee employee);

    List<ReferenceNote> findAllByEmployeeAndDate(Employee employee, Instant date);

    Optional<List<ReferenceNote>> findByEmployeeAndRule(Employee employee, Rule rule);

}
