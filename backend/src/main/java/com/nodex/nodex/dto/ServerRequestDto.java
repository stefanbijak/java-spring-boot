package com.nodex.nodex.dto;

import com.nodex.nodex.entity.enums.Environment;
import com.nodex.nodex.entity.enums.ServerStatus;
import com.nodex.nodex.entity.enums.ServerType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServerRequestDto {

    @NotBlank
    private String hostname;

    @NotNull
    private ServerType serverType;

    private String osType;

    private String rackLocation;

    @NotNull
    private Environment environment;

    @NotNull
    private ServerStatus serverStatus;

    private Integer cpuCores;

    private Integer ramGb;

    private Integer diskGb;

}
