package com.nodex.nodex.service;

import com.nodex.nodex.entity.Server;
import com.nodex.nodex.repository.ServerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {
    private final ServerRepository repository;


    public ServerService(ServerRepository repository) {
        this.repository = repository;
    }

    public List<Server>  getAll() {
        return repository.getAllByActive(true);
    }
}
