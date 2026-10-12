package com.nodex.nodex.mapper;

import com.nodex.nodex.dto.ServerRequestDto;
import com.nodex.nodex.dto.ServerResponseDto;
import com.nodex.nodex.entity.Server;
import org.springframework.stereotype.Component;

@Component
public class ServerMapper {
    public Server toEntity(ServerRequestDto requestDto){
        if (requestDto == null){
            return null;
        }

        Server server = new Server();

        server.setHostname(requestDto.getHostname());

        server.setServerType(requestDto.getServerType());

        server.setOsType(requestDto.getOsType());

        server.setRackLocation(requestDto.getRackLocation());

        server.setEnvironment(requestDto.getEnvironment());

        server.setServerStatus(requestDto.getServerStatus());

        server.setCpuCores(requestDto.getCpuCores());

        server.setRamGb(requestDto.getRamGb());

        server.setDiskGb(requestDto.getDiskGb());

        server.setActive(true);

        return server;
    }

    public ServerResponseDto toDto(Server server){

        if (server == null){
            return null;
        }

        ServerResponseDto responseDto = new ServerResponseDto();

        responseDto.setId(server.getId().toString());

        responseDto.setHostname(server.getHostname());

        responseDto.setServerType(server.getServerType().toString());

        responseDto.setOsType(server.getOsType());

        responseDto.setRackLocation(server.getRackLocation());

        responseDto.setEnvironment(server.getEnvironment().toString());

        responseDto.setServerStatus(server.getServerStatus().toString());

        responseDto.setCpuCores(server.getCpuCores());

        responseDto.setRamGb(server.getRamGb());

        responseDto.setDiskGb(server.getDiskGb());

        return responseDto;
    }
}
