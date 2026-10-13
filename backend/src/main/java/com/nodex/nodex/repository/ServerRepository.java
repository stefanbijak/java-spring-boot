package com.nodex.nodex.repository;

import com.nodex.nodex.entity.Server;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServerRepository extends JpaRepository<Server, Integer> {
    List<Server> findAllByActiveTrue();
    Optional<Server> findByIdAndActiveTrue(Integer id);
}
