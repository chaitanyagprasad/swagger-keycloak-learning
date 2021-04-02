package io.chait.swagger.demo.service;

import io.chait.swagger.demo.dto.EmployeeDTO;
import io.chait.swagger.demo.mapper.EmployeeMapper;
import io.chait.swagger.demo.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimpleEmployeeService implements EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    EmployeeMapper employeeMapper;

    @Override
    public void createEmployee(EmployeeDTO employeeDTO) {
        this.employeeRepo.save(employeeMapper.toEntity(employeeDTO));
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id)  {
        return this.employeeRepo.findById(id).map(employeeMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return this.employeeRepo.findAll().stream().map(employeeMapper::toDto).collect(Collectors.toList());
    }
}
