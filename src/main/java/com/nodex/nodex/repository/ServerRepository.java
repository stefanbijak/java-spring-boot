package com.nodex.nodex.repository;

import com.nodex.nodex.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServerRepository extends JpaRepository<Server, Integer> {
    List<Server> getAllByActive(boolean active);
}
