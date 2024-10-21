package com.example.bankingapp.repository;

import com.example.bankingapp.model.Compte;
import com.example.bankingapp.model.Operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OperationRepository extends JpaRepository<Operation, Long> {

}
