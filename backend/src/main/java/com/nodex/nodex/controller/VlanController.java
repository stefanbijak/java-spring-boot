package com.nodex.nodex.controller;

import com.nodex.nodex.dto.VlanResponseDto;
import com.nodex.nodex.dto.VlanRequestDto;
import com.nodex.nodex.service.VlanService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
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
    public VlanResponseDto getById (@PathVariable Integer id) throws BadRequestException{
        return service.getById(id);
    }

    @GetMapping("/by-number/{number}")
    public VlanResponseDto getByNumber(@PathVariable Integer number) throws BadRequestException{
        return service.getByNumber(number);
    }

    @PutMapping("/{id}")
    public VlanResponseDto update(@PathVariable Integer id, @RequestBody VlanRequestDto request) throws BadRequestException {
        return service.update(id, request);
    }

    @PostMapping
    public VlanResponseDto create(@Valid @RequestBody VlanRequestDto vlan) throws BadRequestException {
        return service.create(vlan);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) throws BadRequestException{
        service.delete(id);
    }
}
