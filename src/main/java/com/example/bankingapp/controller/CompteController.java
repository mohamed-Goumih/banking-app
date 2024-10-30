package com.example.bankingapp.controller;

import com.example.bankingapp.model.Compte;
import com.example.bankingapp.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;
    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }

    @GetMapping("/{id}")
    public Optional<Compte> getCompteById(@PathVariable Long id) {
        return compteService.getCompteById(id);
    }
   /* @PostMapping
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.save(compte);
    }*/

   /* @PostMapping
    public ResponseEntity<?> createCompte(@RequestBody Compte compte) {
        try {
            Compte newCompte = compteService.save(compte);
            return new ResponseEntity<>(newCompte, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }*/
   @PostMapping
   public ResponseEntity<?> createCompte(@RequestBody Compte compte, @RequestParam Long clientId) {
       try {
           // Sauvegarder le compte avec l'ID du client
           Compte newCompte = compteService.save(compte, clientId);
           return new ResponseEntity<>(newCompte, HttpStatus.CREATED);
       } catch (IllegalArgumentException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       } catch (RuntimeException e) {
           return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
       }
   }
    @PutMapping("/{id}")
    public Compte updateCompte(@PathVariable Long id, @RequestBody Compte compteDetails) {
        return compteService.updateCompte(id, compteDetails);
    }
    @DeleteMapping("/{id}")
    public String deleteCompte(@PathVariable Long id) {
        compteService.delete(id);
        return "Compte supprimé avec succès";
    }

    // Endpoint pour verser de l'argent
    @PutMapping("/{id}/verser")
    public ResponseEntity<?> verser(@PathVariable Long id, @RequestParam double montant) {
        try {
            Compte updatedCompte = compteService.verser(id, montant);
            return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint pour retirer de l'argent
    @PutMapping("/{id}/retirer")
    public ResponseEntity<?> retirer(@PathVariable Long id, @RequestParam double montant) {
        try {
            Compte updatedCompte = compteService.retirer(id, montant);
            return new ResponseEntity<>(updatedCompte, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
