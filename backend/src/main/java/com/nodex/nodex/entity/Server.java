package com.nodex.nodex.entity;

import com.nodex.nodex.entity.enums.Environment;
import com.nodex.nodex.entity.enums.ServerStatus;
import com.nodex.nodex.entity.enums.ServerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="server")
public class Server {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="hostname")
    private String hostname;

    @Column(name="domain")
    private String domain;

    @Enumerated(EnumType.STRING)
    @Column(name="server_type")
    private ServerType serverType;

    @Column(name="os_type")
    private String osType;

    @Column(name="rack_location")
    private String rackLocation;

    @Enumerated(EnumType.STRING)
    @Column(name="environment")
    private Environment environment;

    @Enumerated(EnumType.STRING)
    @Column(name="server_status")
    private ServerStatus serverStatus;

    @Column(name="maintenance_ip")
    private String maintenanceIp;

    @Column(name="cpu_cores")
    private Integer cpuCores;

    @Column(name="ram_gb")
    private Integer ramGb;

    @Column(name="disk_gb")
    private Integer diskGb;

    @Column(name="active")
    private Boolean active;
}
