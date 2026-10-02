package com.nodex.nodex.service;

import com.nodex.nodex.entity.Subnet;
import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.dto.SubnetRequestDto;
import com.nodex.nodex.dto.SubnetResponseDto;
import com.nodex.nodex.mapper.SubnetMapper;
import com.nodex.nodex.repository.SubnetRepository;
import com.nodex.nodex.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.hibernate.boot.jaxb.internal.stax.AbstractEventReader;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class SubnetService {
    private final SubnetRepository repository;
    private final SubnetMapper subnetMapper;
    private final VlanRepository vlanRepository;

    public SubnetService(SubnetRepository repository, SubnetMapper subnetMapper, VlanRepository vlanRepository) {
        this.repository = repository;
        this.subnetMapper = subnetMapper;
        this.vlanRepository = vlanRepository;
    }

    public List<SubnetResponseDto> getAll(){
        List<Subnet> subnets = repository.findAllByActive(true);
        return subnets.stream().map(subnet ->toDto(subnet)).toList();
    }

    public SubnetResponseDto getById(Integer id) throws BadRequestException{
        Subnet subnet = repository.findById(id).orElseThrow(() ->new BadRequestException("Subnet by ID:" + id + " not found!"));
        return toDto(subnet);
    }

    @Transactional
    public SubnetResponseDto create(SubnetRequestDto dto) throws BadRequestException{
        Vlan vlan = vlanRepository.findById(dto.getVlanId()).orElseThrow(() -> new BadRequestException("Vlan with ID: " + dto.getVlanId() + " does not exist!"));

        Subnet subnet = subnetMapper.toEntity(dto, vlan);

        Subnet newSubnet = repository.saveAndFlush(subnet);

        return toDto(newSubnet);
    }

    @Transactional
    public SubnetResponseDto update(Integer id, SubnetRequestDto request) throws BadRequestException{
        Subnet subnet = repository.findById(id).orElseThrow(() -> new BadRequestException("Subnet with ID " + id + " does not exist!"));

        Vlan vlan = vlanRepository.findById(request.getVlanId()).orElseThrow(() -> new BadRequestException("Vlan with ID: " + request.getVlanId() + " does not exist!"));

        subnetMapper.updateEntityFromDto(subnet, request, vlan);

        Subnet updatedSubnet = repository.save(subnet);

        return toDto(updatedSubnet);
    }

    @Transactional
    public void delete(Integer id) throws BadRequestException{
        Subnet subnet = repository.findById(id).orElseThrow(()-> new BadRequestException("Subnet with ID: " + id + " not found!"));
        subnet.setActive(false);
        repository.save(subnet);
    }

    // HELPERS FUNCTIONS

    private SubnetResponseDto toDto(Subnet subnet){
        return subnetMapper.toDto(subnet);
    }
}
