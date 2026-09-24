package com.nodex.nodex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="subnet")
public class Subnet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vlan_id", nullable = false)
    private Vlan vlan;

    @Column(name="network", nullable = false)
    private String network;

    @Column(name="cidr", nullable = false)
    private Integer cidr;

    @Column(name="netmask")
    private String netmask;

    @Column(name="dns_primary")
    private String dnsPrimary;

    @Column(name="dns_secondary")
    private String dnsSecondary;

    @Column(name="active")
    private Boolean active = true;
}
