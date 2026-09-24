package com.nodex.nodex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name="server_interface")
public class ServerInterface {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "serverInterface", cascade = CascadeType.ALL)
    private List<IpAddress> ipAddresses;

    @Column(name="name")
    private String name;

    @Column(name="mac_address", unique = true)
    private String macAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="server_id", nullable = false)
    private Server server;

    @Column(name="active")
    private Boolean active = true;
}
