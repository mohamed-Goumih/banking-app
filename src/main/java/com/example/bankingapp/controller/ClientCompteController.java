package com.example.bankingapp.controller;

import com.example.bankingapp.model.Compte;
import com.example.bankingapp.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/client/comptes")
public class ClientCompteController {

    @Autowired
    private CompteService compteService;

    @PutMapping("/{id}/verser")
    public Compte verser(@PathVariable Long id, @RequestParam double montant) {
        // Récupérer l'utilisateur connecté
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Vérifier que le compte appartient bien au client connecté
        Compte compte = compteService.getCompteByIdAndUsername(id, username)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé ou vous n'êtes pas autorisé à faire cette opération."));

        return compteService.verser(id, montant);
    }
}
