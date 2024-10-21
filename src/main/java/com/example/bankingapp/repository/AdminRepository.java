package com.example.bankingapp.repository;

import com.example.bankingapp.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin,Long> {
}