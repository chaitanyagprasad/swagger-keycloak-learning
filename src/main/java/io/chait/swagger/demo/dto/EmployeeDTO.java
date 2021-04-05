package io.chait.swagger.demo.dto;

import io.chait.swagger.demo.enums.Gender;
import lombok.*;

@Getter@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
public class EmployeeDTO {

    private String name;
    private Gender gender;
    private Integer age;

}
