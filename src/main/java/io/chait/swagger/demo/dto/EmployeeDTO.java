package io.chait.swagger.demo.dto;

import io.chait.swagger.demo.enums.Gender;
import lombok.Data;

@Data
public class EmployeeDTO {

    private String name;
    private Gender gender;
    private Integer age;

}
