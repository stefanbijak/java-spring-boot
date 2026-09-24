package com.nodex.nodex.controller;

import com.nodex.nodex.entity.Subnet;
import com.nodex.nodex.entity.dto.SubnetRequestDto;
import com.nodex.nodex.entity.dto.SubnetResponseDto;
import com.nodex.nodex.service.SubnetService;
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
    public List<SubnetResponseDto> getAll(@RequestParam(required = false) Boolean active) {
        return service.getAll(active);
    }

    @GetMapping("/{id}")
    public SubnetResponseDto get(@PathVariable Integer id){
        return service.get(id);
    }

    @PostMapping
    public SubnetResponseDto create(@RequestBody SubnetRequestDto subnet){
        return service.create(subnet);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
