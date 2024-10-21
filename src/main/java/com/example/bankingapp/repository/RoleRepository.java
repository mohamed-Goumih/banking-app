package com.example.bankingapp.repository;

import com.example.bankingapp.model.Role;
import com.example.bankingapp.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

