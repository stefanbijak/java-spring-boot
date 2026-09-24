package com.nodex.nodex.entity;

import com.nodex.nodex.entity.enums.IpStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ip_address")
public class IpAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="subnet_id",nullable = false)
    private Subnet subnet;

    @Column(name="ip")
    private String ip;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private IpStatus status = IpStatus.FREE;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="server_interface_id")
    private ServerInterface serverInterface;

    @Column(name="description")
    private String description;

    @Column(name="active")
    private Boolean active = true;
}
