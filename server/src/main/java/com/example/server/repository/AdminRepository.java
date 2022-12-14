package com.example.server.repository;

import com.example.server.entity.Admin;
import com.example.server.entity.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Long> {
    Optional<Admin> findByUzerId(Uzer userId);
}
