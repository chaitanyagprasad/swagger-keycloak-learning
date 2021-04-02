package io.chait.swagger.demo.mapper;

import io.chait.swagger.demo.dto.EmployeeDTO;
import io.chait.swagger.demo.repo.EmployeeEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDTO toDto(EmployeeEntity entity);

    EmployeeEntity toEntity(EmployeeDTO dto);

}
