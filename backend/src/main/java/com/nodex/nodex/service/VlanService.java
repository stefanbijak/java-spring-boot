package com.nodex.nodex.service;

import com.nodex.nodex.entity.Vlan;
import com.nodex.nodex.dto.VlanResponseDto;
import com.nodex.nodex.dto.VlanRequestDto;
import com.nodex.nodex.mapper.VlanMapper;
import com.nodex.nodex.repository.VlanRepository;
import org.apache.coyote.BadRequestException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class VlanService {
    private final VlanRepository repository;
    private final VlanMapper vlanMapper;

    public VlanService(VlanRepository repository, VlanMapper vlanMapper, ModelMapper modelMapper) {
        this.repository = repository;
        this.vlanMapper = vlanMapper;
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
            throw new BadRequestException("Vlan " + request.getNumber() + " already exists!");
        }

        vlanMapper.updateEntityFromDto(vlan, request);

        Vlan updatedVlan = repository.save(vlan);

        return toDto(updatedVlan);
    }

    @Transactional
    public VlanResponseDto create(VlanRequestDto vlanDto) throws BadRequestException{

        if (repository.existsByNumber(vlanDto.getNumber())){
            throw new BadRequestException("Vlan with number " + vlanDto.getNumber() + " already exists");
        }

        Vlan vlan = vlanMapper.toEntity(vlanDto);

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
        return vlanMapper.toDto(vlan);
    }
}
