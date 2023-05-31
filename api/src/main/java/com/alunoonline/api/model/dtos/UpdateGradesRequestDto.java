package com.alunoonline.api.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateGradesRequestDto {
    private Double nota1;
    private Double nota2;
}
