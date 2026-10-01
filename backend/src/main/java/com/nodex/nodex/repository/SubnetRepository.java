package com.nodex.nodex.repository;

import com.nodex.nodex.entity.Subnet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubnetRepository extends JpaRepository<Subnet, Integer> {
    List<Subnet> findAllByActive(boolean active);
}
