package com.nodex.nodex.service;

import com.nodex.nodex.dto.ServerRequestDto;
import com.nodex.nodex.dto.ServerResponseDto;
import com.nodex.nodex.entity.Server;
import com.nodex.nodex.exception.ServerNotFoundException;
import com.nodex.nodex.mapper.ServerMapper;
import com.nodex.nodex.repository.ServerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ServerService {
    private final ServerRepository repository;
    private final ServerMapper serverMapper;

    public ServerService(ServerRepository repository, ServerMapper serverMapper) {
        this.repository = repository;
        this.serverMapper = serverMapper;
    }

    @Transactional(readOnly = true)
    public List<ServerResponseDto> getAll() {
        List<Server> servers = repository.findAllByActiveTrue();
        return servers.stream().map(server -> toDto(server)).toList();
    }

    public ServerResponseDto create(ServerRequestDto requestDto) {

        Server server = serverMapper.toEntity(requestDto);

        Server newServer = repository.saveAndFlush(server);

        return toDto(newServer);
    }

    public ServerResponseDto update(Integer id, ServerRequestDto requestDto) {
        Server server = findOrThrow(id);

        serverMapper.updateEntityFromDto(requestDto, server);

        return toDto(server);

    }

    public void delete(Integer id) {
        Server server = findOrThrow(id);
        server.setActive(false);
    }

    //HELPERS FUNCTIONS

    private ServerResponseDto toDto(Server server) {
        return serverMapper.toDto(server);
    }

    private Server findOrThrow(Integer id) {
        return repository.findByIdAndActiveTrue(id).orElseThrow(() -> new ServerNotFoundException(id));
    }
}
