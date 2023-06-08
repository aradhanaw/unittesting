package com.example.learn.boot.unittest.service;

import com.example.learn.boot.unittest.model.EmployeeDTO;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    List<EmployeeDTO> listAll();

    EmployeeDTO save(EmployeeDTO dto);


    EmployeeDTO updateEmployee(Long id, EmployeeDTO updatedEmployee);

    EmployeeDTO getEmployeeById(long id);

    EmployeeDTO deleteEmployeeById(long id);

//    EmployeeDTO updateEmployee(long id,EmployeeDTO updatedEmployee);


}
