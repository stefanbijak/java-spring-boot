package com.nodex.nodex.mapper;

import com.nodex.nodex.dto.VlanRequestDto;
import com.nodex.nodex.dto.VlanResponseDto;
import com.nodex.nodex.entity.Vlan;
import org.springframework.stereotype.Component;

@Component
public class VlanMapper {
    public Vlan toEntity(VlanRequestDto dto){
        if (dto == null){
            return null;
        }

        Vlan vlan = new Vlan();

        vlan.setName(dto.getName());
        vlan.setNumber(dto.getNumber());
        vlan.setDescription(dto.getDescription());
        vlan.setActive(true);

        return vlan;
    }

    public VlanResponseDto toDto(Vlan vlan){
        if (vlan == null){
            return null;
        }

        VlanResponseDto dto = new VlanResponseDto();

        dto.setId(vlan.getId());
        dto.setName(vlan.getName());
        dto.setNumber(vlan.getNumber());
        dto.setDescription(vlan.getDescription());

        return dto;
    }

    public void updateEntityFromDto(Vlan vlan, VlanRequestDto request){
        vlan.setName(request.getName());
        vlan.setNumber(request.getNumber());
        vlan.setDescription(request.getDescription());
    }
}
