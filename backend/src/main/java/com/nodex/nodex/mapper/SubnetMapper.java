package com.nodex.nodex.mapper;

import com.nodex.nodex.dto.SubnetRequestDto;
import com.nodex.nodex.dto.SubnetResponseDto;
import com.nodex.nodex.entity.Subnet;
import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.repository.VlanRepository;
import org.springframework.stereotype.Component;

@Component
public class SubnetMapper {

    private final VlanMapper vlanMapper;
    private final VlanRepository vlanRepository;

    public SubnetMapper(VlanMapper vlanMapper, VlanRepository vlanRepository) {
        this.vlanMapper = vlanMapper;
        this.vlanRepository = vlanRepository;
    }

    public Subnet toEntity(SubnetRequestDto dto, Vlan vlan){
        if (dto == null){
            return null;
        }

        Subnet subnet = new Subnet();

        subnet.setVlan(vlan);
        subnet.setNetwork(dto.getNetwork());
        subnet.setCidr(dto.getCidr());
        subnet.setNetmask(dto.getNetmask());
        subnet.setDnsPrimary(dto.getDnsPrimary());
        subnet.setDnsSecondary(dto.getDnsSecondary());

        return subnet;
    }

    public SubnetResponseDto toDto(Subnet subnet){

        if(subnet == null){
            return null;
        }

        SubnetResponseDto dto = new SubnetResponseDto();

        dto.setId(subnet.getId());
        dto.setVlan(vlanMapper.toDto(subnet.getVlan()));
        dto.setNetwork(subnet.getNetwork());
        dto.setCidr(subnet.getCidr());
        dto.setNetmask(subnet.getNetmask());
        dto.setDnsPrimary(subnet.getDnsPrimary());
        dto.setDnsSecondary(subnet.getDnsSecondary());

        return dto;
    }

    public void updateEntityFromDto(Subnet subnet, SubnetRequestDto request, Vlan vlan){
        subnet.setVlan(vlan);
        subnet.setNetwork(request.getNetwork());
        subnet.setCidr(request.getCidr());
        subnet.setNetmask(request.getNetmask());
        subnet.setDnsPrimary(request.getDnsPrimary());
        subnet.setDnsSecondary(request.getDnsSecondary());
    }
}
