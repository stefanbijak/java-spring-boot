package com.nodex.nodex.repository;

import com.nodex.nodex.entity.Vlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VlanRepository extends JpaRepository<Vlan, Integer> {
    List<Vlan> getAllByActive(boolean active);
    Optional<Vlan> findByIdAndActiveTrue(Integer id);
    Optional<Vlan> findByNumberAndActiveTrue(Integer id);
    Boolean existsByNumber(Integer number);
}
