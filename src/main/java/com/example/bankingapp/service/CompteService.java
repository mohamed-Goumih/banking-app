package com.example.bankingapp.service;

import com.example.bankingapp.model.Client;
import com.example.bankingapp.model.Compte;
import com.example.bankingapp.model.Operation;
import com.example.bankingapp.repository.ClientRepository;
import com.example.bankingapp.repository.CompteRepository;
import com.example.bankingapp.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CompteService {

    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private ClientRepository clientRepository;
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompteById(Long id) {
        return compteRepository.findById(id);
    }
    //public Compte save(Compte compte) {
       // return compteRepository.save(compte);
    //}

  /*  public Compte save(Compte compte) {
        // Vérifier si un compte avec le même numéro existe déjà
        Optional<Compte> existingCompte = compteRepository.findByNumeroCompte(compte.getNumeroCompte());
        if (existingCompte.isPresent()) {
            throw new IllegalArgumentException("Un compte avec ce numéro existe déjà.");
        }

        return compteRepository.save(compte);
    }*/

    public Compte save(Compte compte, Long clientId) {
        // Rechercher le client par ID
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + clientId));

        // Associer le client au compte
        compte.setClient(client);

        // Sauvegarder et retourner le compte
        return compteRepository.save(compte);
    }
    public Optional<Compte> findById(Long id) {
        return compteRepository.findById(id);
    }

    public Compte updateCompte(Long id, Compte compteDetails) {
        // Trouver le compte existant
        Compte existingCompte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec id: " + id));

        // Mettre à jour les informations du compte
        existingCompte.setNumeroCompte(compteDetails.getNumeroCompte());
        existingCompte.setSolde(compteDetails.getSolde());

        // Sauvegarder et retourner le compte mis à jour
        return compteRepository.save(existingCompte);
    }

    public void delete(Long id) {
        compteRepository.deleteById(id);
    }

    /*// Méthode pour verser de l'argent
    public Compte verser(Long id, double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec id: " + id));

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à verser doit être supérieur à zéro.");
        }

        // Augmenter le solde du compte
        compte.setSolde(compte.getSolde() + montant);
        return compteRepository.save(compte);
    }

    // Méthode pour retirer de l'argent
    public Compte retirer(Long id, double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec id: " + id));

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à retirer doit être supérieur à zéro.");
        }

        if (compte.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le retrait.");
        }

        // Diminuer le solde du compte
        compte.setSolde(compte.getSolde() - montant);
        return compteRepository.save(compte);
    }*/

    @Autowired
    private OperationRepository operationRepository;

    // Méthode pour verser de l'argent
    public Compte verser(Long id, double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec id: " + id));

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à verser doit être supérieur à zéro.");
        }

        // Augmenter le solde du compte
        compte.setSolde(compte.getSolde() + montant);

        // Créer une nouvelle opération
        Operation operation = new Operation();
        operation.setTypeOperation("Dépôt");
        operation.setMontant(montant);
        operation.setDateOperation(new Date());
        operation.setCompte(compte);

        // Sauvegarder l'opération
        operationRepository.save(operation);

        // Sauvegarder le compte mis à jour
        return compteRepository.save(compte);
    }

    // Méthode pour retirer de l'argent
    public Compte retirer(Long id, double montant) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé avec id: " + id));

        if (montant <= 0) {
            throw new IllegalArgumentException("Le montant à retirer doit être supérieur à zéro.");
        }

        if (compte.getSolde() < montant) {
            throw new IllegalArgumentException("Solde insuffisant pour effectuer le retrait.");
        }

        // Diminuer le solde du compte
        compte.setSolde(compte.getSolde() - montant);

        // Créer une nouvelle opération
        Operation operation = new Operation();
        operation.setTypeOperation("Retrait");
        operation.setMontant(montant);
        operation.setDateOperation(new Date());
        operation.setCompte(compte);

        // Sauvegarder l'opération
        operationRepository.save(operation);

        // Sauvegarder le compte mis à jour
        return compteRepository.save(compte);
    }

    public Optional<Compte> getCompteByIdAndUsername(Long id, String username) {
        return compteRepository.findByIdAndClientUsername(id, username);
    }

}
