package com.nodex.nodex.service;

import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.entity.dto.VlanResponseDto;
import com.nodex.nodex.entity.dto.VlanRequestDto;
import com.nodex.nodex.repository.VlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VlanService {
    private final VlanRepository repository;

    private final ModelMapper modelMapper;

    public VlanService(VlanRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    public List<VlanResponseDto> getAll() {
        List<Vlan> vlans = repository.getAllByActive(true);
        return vlans.stream().map(vlan -> toDto(vlan)).toList();
    }

    public VlanResponseDto getById(Integer id) throws BadRequestException {
        Vlan vlan = repository.findByIdAndActiveTrue(id).orElseThrow(() -> new BadRequestException("Vlan by ID: " + id + " not found!"));
        return toDto(vlan);
    }

    public VlanResponseDto getByNumber(Integer number) throws BadRequestException{
        Vlan vlan = repository.findByNumberAndActiveTrue(number).orElseThrow(() -> new BadRequestException("Vlan " + number + " not found!"));
        return toDto(vlan);
    }

    @Transactional
    public VlanResponseDto update(Integer id, VlanRequestDto request) throws BadRequestException {
        Vlan vlan = repository.findById(id).orElseThrow(() -> new BadRequestException("Vlan by ID: " + id + " not found!"));

        if (!request.getNumber().equals(vlan.getNumber()) && repository.existsByNumber(request.getNumber())) {
            throw new BadRequestException("Vlan " + vlan.getNumber() + " already exists!");
        }

        vlan.setNumber(request.getNumber());
        vlan.setName(request.getName());
        vlan.setDescription(request.getDescription());
        vlan.setActive(request.getActive());

        Vlan updatedVlan = repository.save(vlan);

        return toDto(updatedVlan);
    }

    @Transactional
    public VlanResponseDto create(VlanRequestDto vlanDto) throws BadRequestException{

        if (repository.existsByNumber(vlanDto.getNumber())){
            throw new BadRequestException("Vlan with number " + vlanDto.getNumber() + " already exists");
        }

        Vlan vlan = new Vlan();
        vlan.setNumber(vlanDto.getNumber());
        vlan.setName(vlanDto.getName());
        vlan.setDescription(vlanDto.getDescription());
        vlan.setActive(true);

        Vlan newVlan = repository.saveAndFlush(vlan);

        return toDto(newVlan);
    }

    @Transactional
    public void delete(Integer id) throws BadRequestException {
        Vlan vlan = repository.findById(id).orElseThrow(() -> new BadRequestException("Vlan by ID: " + id + " not found!"));
        vlan.setActive(false);
        repository.save(vlan);
    }

    // HELPER FUNCTIONS

    private VlanResponseDto toDto(Vlan vlan){
        return modelMapper.map(vlan, VlanResponseDto.class);
    }
}
