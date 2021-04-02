package io.chait.swagger.demo.service;

import io.chait.swagger.demo.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {

    void createEmployee(EmployeeDTO employeeDTO);

    EmployeeDTO getEmployeeById(Long id);

    List<EmployeeDTO> getAllEmployees();

}
