package com.nodex.nodex.controller;

import com.nodex.nodex.dto.ServerRequestDto;
import com.nodex.nodex.dto.ServerResponseDto;
import com.nodex.nodex.service.ServerService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {
    private final ServerService service;

    public ServerController(ServerService service){
        this.service=service;
    }

    @GetMapping
    public List<ServerResponseDto> getAll(){
        return service.getAll();
    }

    @PostMapping
    public ServerResponseDto create(@Valid @RequestBody ServerRequestDto requestDto) throws BadRequestException{
        return service.create(requestDto);
    }

    @PutMapping("/{id}")
    public ServerResponseDto update(@PathVariable Integer id, @Valid @RequestBody ServerRequestDto requestDto) throws BadRequestException{
        return service.update(id, requestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) throws BadRequestException{
        service.delete(id);
    }
}


