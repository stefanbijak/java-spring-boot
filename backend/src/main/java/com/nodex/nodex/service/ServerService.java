package com.nodex.nodex.service;

import com.nodex.nodex.dto.ServerRequestDto;
import com.nodex.nodex.dto.ServerResponseDto;
import com.nodex.nodex.entity.Server;
import com.nodex.nodex.mapper.ServerMapper;
import com.nodex.nodex.repository.ServerRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServerService {
    private final ServerRepository repository;
    private final ServerMapper serverMapper;

    public ServerService(ServerRepository repository, ServerMapper serverMapper) {
        this.repository = repository;
        this.serverMapper = serverMapper;
    }

    public List<Server>  getAll() throws BadRequestException {
        return repository.getAllByActive(true);
    }

    public ServerResponseDto create(ServerRequestDto requestDto) throws BadRequestException{

        Server server = serverMapper.toEntity(requestDto);

        Server newServer = repository.saveAndFlush(server);

        return toDto(newServer);
    }

    //HELPERS FUNCTIONS

    private ServerResponseDto toDto(Server server) {
        return serverMapper.toDto(server);
    }
}
