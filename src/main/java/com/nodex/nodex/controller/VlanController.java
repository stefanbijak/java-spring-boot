package com.nodex.nodex.controller;

import com.nodex.nodex.entity.dto.VlanResponseDto;
import com.nodex.nodex.entity.dto.VlanRequestDto;
import com.nodex.nodex.service.VlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vlans")
public class VlanController {
    private final VlanService service;

    public VlanController(VlanService service) {
        this.service = service;
    }

    @GetMapping
    public List<VlanResponseDto> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public VlanResponseDto getById(@PathVariable Integer id){
        return service.getById(id);
    }

    @GetMapping("/by-tag/{id}")
    public VlanResponseDto getByTag(@PathVariable Integer id){
        return service.getByTag(id);
    }

    @PutMapping("/{id}")
    public VlanResponseDto update(@PathVariable Integer id, @RequestBody VlanRequestDto request){
        return service.update(id, request);
    }

    @PostMapping
    public VlanResponseDto create(@RequestBody VlanRequestDto vlan) {
        return service.create(vlan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id){
        service.delete(id);
    }
}
