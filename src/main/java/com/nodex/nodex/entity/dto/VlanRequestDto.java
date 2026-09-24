package com.nodex.nodex.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class VlanRequestDto {

    private Integer number;

    private String name;

    private String description;

    private Boolean active;
}
