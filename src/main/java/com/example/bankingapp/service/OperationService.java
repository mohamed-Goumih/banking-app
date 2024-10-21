package com.example.bankingapp.service;

import com.example.bankingapp.model.Operation;
import com.example.bankingapp.repository.OperationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OperationService {

    @Autowired
    private OperationRepository operationRepository;
    public List<Operation> getAllOperations() {
        return operationRepository.findAll();
    }

    public Optional<Operation> getOperationById(Long id) {
        return operationRepository.findById(id);
    }
    public Operation save(Operation operation) {
        return operationRepository.save(operation);
    }

    public Optional<Operation> findById(Long id) {
        return operationRepository.findById(id);
    }

    public Operation updateOperation(Long id, Operation operationDetails) {
        // Trouver l'opération existante
        Operation existingOperation = operationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Opération non trouvée avec id: " + id));

        // Mettre à jour les informations de l'opération
        existingOperation.setTypeOperation(operationDetails.getTypeOperation());
        existingOperation.setMontant(operationDetails.getMontant());
        existingOperation.setDateOperation(operationDetails.getDateOperation());

        // Sauvegarder et retourner l'opération mise à jour
        return operationRepository.save(existingOperation);
    }

    public void delete(Long id) {
        operationRepository.deleteById(id);
    }
}
