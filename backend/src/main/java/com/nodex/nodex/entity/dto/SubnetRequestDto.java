package com.nodex.nodex.entity.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubnetRequestDto {
    @NotNull
    private Integer vlanId;

    @NotBlank
    private String network;

    @NotNull
    @Min(0)
    @Max(32)
    private Integer cidr;

    private String dnsPrimary;

    private String dnsSecondary;
}
