package com.iba.referenceList.repository;

import com.iba.referenceList.domain.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface EmployeeRepository extends JpaRepository<Employee, UUID> {

    List<Employee> findAllByBoss(Employee employee);

    Page<Employee> findAll(Pageable pageable);

}
