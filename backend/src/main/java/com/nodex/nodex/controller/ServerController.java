package com.nodex.nodex.controller;

import com.nodex.nodex.dto.ServerRequestDto;
import com.nodex.nodex.dto.ServerResponseDto;
import com.nodex.nodex.entity.Server;
import com.nodex.nodex.service.ServerService;
import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Transactional
@RestController
@RequestMapping("/servers")
public class ServerController {
    private final ServerService service;

    public ServerController(ServerService service){
        this.service=service;
    }

    @GetMapping
    public List<Server> getAll() throws BadRequestException {
        return service.getAll();
    }

    @Transactional
    @PostMapping
    public ServerResponseDto create(@Valid @RequestBody ServerRequestDto requestDto) throws BadRequestException{
        return service.create(requestDto);
    }
}


