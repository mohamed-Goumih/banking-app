package com.example.bankingapp.repository;

import com.example.bankingapp.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {

    //A Ajouter apres les CRUDS
    //puis implementer dans le service
    // Rechercher un client par email
    Optional<Client> findByEmail(String email);
}

