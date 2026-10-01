package com.nodex.nodex.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VlanRequestDto {

    @NotNull
    @Min(1)
    @Max(4094)
    private Integer number;

    private String name;

    private String description;
}
