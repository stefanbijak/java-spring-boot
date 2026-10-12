package com.nodex.nodex.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerResponseDto {
    private String id;

    private String hostname;

    private String domain;

    private String serverType;

    private String osType;

    private String rackLocation;

    private String environment;

    private String serverStatus;

    private Integer cpuCores;

    private Integer ramGb;

    private Integer diskGb;
}
