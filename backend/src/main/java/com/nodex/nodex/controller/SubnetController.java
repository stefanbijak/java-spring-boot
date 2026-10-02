package com.nodex.nodex.controller;

import com.nodex.nodex.dto.SubnetRequestDto;
import com.nodex.nodex.dto.SubnetResponseDto;
import com.nodex.nodex.service.SubnetService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subnets")
public class SubnetController {

    private final SubnetService service;

    public SubnetController(SubnetService service) {
        this.service = service;
    }

    @GetMapping
    public List<SubnetResponseDto> getAll(){
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SubnetResponseDto getById(@PathVariable Integer id) throws BadRequestException {
        return service.getById(id);
    }

    @PostMapping
    public SubnetResponseDto create(@Valid @RequestBody SubnetRequestDto dto) throws BadRequestException{
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public SubnetResponseDto update(@Valid @RequestBody SubnetRequestDto dto, @PathVariable Integer id) throws BadRequestException{
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws BadRequestException{
        service.delete(id);
    }
}
