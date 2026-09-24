package com.nodex.nodex.entity.dto;

import com.nodex.nodex.entity.Vlan;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class SubnetResponseDto {
    private Integer id;
    private VlanResponseDto vlan;
    private String network;
    private Integer cidr;
    private String netmask;
    private String dnsPrimary;
    private String dnsSecondary;
}
