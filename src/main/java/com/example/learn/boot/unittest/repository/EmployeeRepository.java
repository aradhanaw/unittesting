package com.example.learn.boot.unittest.repository;

import com.example.learn.boot.unittest.domain.PersistentEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<PersistentEmployeeEntity,Long> {

}
