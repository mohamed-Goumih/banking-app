package com.example.bankingapp.controller;

import com.example.bankingapp.model.Operation;
import com.example.bankingapp.service.OperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/operations")
public class OperationController {

    @Autowired
    private OperationService operationService;

    @GetMapping
    public List<Operation> getAllOperations() {
        return operationService.getAllOperations();
    }

    @GetMapping("/{id}")
    public Optional<Operation> getOperationById(@PathVariable Long id) {
        return operationService.getOperationById(id);
    }

    @PostMapping
    public Operation createOperation(@RequestBody Operation operation) {
        return operationService.save(operation);
    }

    @PutMapping("/{id}")
    public Operation updateOperation(@PathVariable Long id, @RequestBody Operation operationDetails) {
        return operationService.updateOperation(id, operationDetails);
    }
    @DeleteMapping("/{id}")
    public String deleteOperation(@PathVariable Long id) {
        operationService.delete(id);
        return "Opération supprimée avec succès";
    }
}
