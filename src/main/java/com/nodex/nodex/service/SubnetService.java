package com.nodex.nodex.service;

import com.nodex.nodex.entity.Subnet;
import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.entity.dto.SubnetRequestDto;
import com.nodex.nodex.entity.dto.SubnetResponseDto;
import com.nodex.nodex.repository.SubnetRepository;
import com.nodex.nodex.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubnetService {
    private final SubnetRepository repository;
    private final VlanRepository vlanRepository;
    private final ModelMapper modelMapper;

    public SubnetService(SubnetRepository repository, VlanRepository vlanRepository, ModelMapper modelMapper) {
        this.repository = repository;
        this.vlanRepository = vlanRepository;
        this.modelMapper = modelMapper;
    }

    public List<SubnetResponseDto> getAll(Boolean active) {
        List<Subnet> subnets;
        if (active != null) {
            subnets = repository.findAllByActive(active);
        } else {
            subnets = repository.findAll();
        }
        return subnets.stream()
                .map(subnet -> modelMapper.map(subnet, SubnetResponseDto.class))
                .toList();
    }

    public SubnetResponseDto get(Integer id){
        Subnet subnet = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subnet not found"));
        return modelMapper.map(subnet, SubnetResponseDto.class);
    }

    public SubnetResponseDto create(SubnetRequestDto subnetDto){
        Vlan vlan = vlanRepository.findByIdAndActiveTrue(subnetDto.getVlanId()).orElseThrow(() -> new EntityNotFoundException("Vlan not found"));
        Subnet subnet = new Subnet();
        subnet.setVlan(vlan);
        subnet.setNetwork(subnetDto.getNetwork());
        subnet.setCidr(subnetDto.getCidr());
        subnet.setDnsPrimary(subnetDto.getDnsPrimary());
        subnet.setDnsSecondary(subnetDto.getDnsSecondary());
        Subnet newSubnet = repository.saveAndFlush(subnet);
        return modelMapper.map(newSubnet, SubnetResponseDto.class);
    }

    public void delete(Integer id){
        Subnet subnet = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Subnet not found!"));
        subnet.setActive(false);
        repository.save(subnet);
    }
}
