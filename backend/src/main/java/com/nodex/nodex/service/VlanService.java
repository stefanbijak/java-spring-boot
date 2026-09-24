package com.nodex.nodex.service;

import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.entity.dto.VlanResponseDto;
import com.nodex.nodex.entity.dto.VlanRequestDto;
import com.nodex.nodex.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VlanService {
    private final VlanRepository repository;

    private final ModelMapper modelMapper;

    public VlanService(VlanRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<VlanResponseDto> getAll() {
        List<Vlan> vlans = repository.getAllByActive(true);
        return vlans.stream().map(vlan -> modelMapper.map(vlan, VlanResponseDto.class)).toList();
    }

    public VlanResponseDto getById(Integer id){
        Vlan vlan = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new EntityNotFoundException("Not Found!"));
        return modelMapper.map(vlan, VlanResponseDto.class);
    }

    public VlanResponseDto getByTag(Integer id){
        Vlan vlan = repository.findByNumberAndActiveTrue(id).orElseThrow(() -> new EntityNotFoundException("Not found"));
        return modelMapper.map(vlan, VlanResponseDto.class);
    }

    public VlanResponseDto update(Integer id, VlanRequestDto request){
        Vlan vlan = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Vlan not found"));

        if(!(vlan.getNumber()==request.getNumber()) && repository.existsByNumber(request.getNumber())){
            throw new IllegalArgumentException("VLAN with number " + request.getNumber() + " already exists!");
        }

        vlan.setNumber(request.getNumber());
        vlan.setName(request.getName());
        vlan.setDescription(request.getDescription());
        vlan.setActive(request.getActive());

        Vlan updatedVlan = repository.save(vlan);

        return modelMapper.map(updatedVlan, VlanResponseDto.class);
    }

    public VlanResponseDto create(VlanRequestDto vlanDto) {
        Vlan vlan = new Vlan();
        vlan.setNumber(vlanDto.getNumber());
        vlan.setName(vlanDto.getName());
        vlan.setDescription(vlanDto.getDescription());
        vlan.setActive(true);
        Vlan newVlan = repository.saveAndFlush(vlan);
        return modelMapper.map(newVlan, VlanResponseDto.class);
    }

    public void delete(Integer id){
        Vlan vlan = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Not Found!"));
        vlan.setActive(false);
        repository.save(vlan);
    }
}
