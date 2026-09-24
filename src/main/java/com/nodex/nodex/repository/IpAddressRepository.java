package com.nodex.nodex.repository;

import com.nodex.nodex.entity.IpAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IpAddressRepository extends JpaRepository<IpAddress, Integer> {
}
