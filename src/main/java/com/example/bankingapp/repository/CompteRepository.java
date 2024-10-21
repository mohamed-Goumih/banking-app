package com.example.bankingapp.repository;

import com.example.bankingapp.model.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompteRepository extends JpaRepository<Compte, Long> {
    //A ajouter apres les CRUDS
    // Rechercher un compte par son numéro de compte
    Optional<Compte> findByNumeroCompte(String numeroCompte);
    Optional<Compte> findByIdAndClientUsername(Long id, String username);
}
