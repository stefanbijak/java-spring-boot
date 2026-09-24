package com.nodex.nodex.controller;

import com.nodex.nodex.entity.Server;
import com.nodex.nodex.service.ServerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/servers")
public class ServerController {
    private final ServerService service;

    public ServerController(ServerService service){
        this.service=service;
    }

    public List<Server> getAll(){
        return service.getAll();
    }
}


