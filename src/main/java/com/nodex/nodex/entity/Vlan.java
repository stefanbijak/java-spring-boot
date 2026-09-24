package com.nodex.nodex.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="vlan")
public class Vlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="number", unique = true, nullable = false)
    private Integer number;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="active")
    private Boolean active;
}
