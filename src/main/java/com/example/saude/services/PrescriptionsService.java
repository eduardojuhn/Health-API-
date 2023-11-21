package com.example.saude.services;

import com.example.saude.model.PrescriptionsModel;
import com.example.saude.repositories.PrescriptionsRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PrescriptionsService {

    final PrescriptionsRepository prescriptionsRepository;

    public PrescriptionsService(PrescriptionsRepository prescriptionsRepository) {
        this.prescriptionsRepository = prescriptionsRepository;
    }

    @Transactional
    public PrescriptionsModel save(PrescriptionsModel prescriptionModel) {
        return prescriptionsRepository.save(prescriptionModel);
    }

    public boolean existsByDiagnostic(String diagnostic) {
        return prescriptionsRepository.existsByDiagnostic(diagnostic);
    }

    public List<PrescriptionsModel> findAll() {
        return prescriptionsRepository.findAll();
    }

    public Optional<PrescriptionsModel> findById(UUID id) {
        return prescriptionsRepository.findById(id);
    }

    @Transactional
    public void delete(PrescriptionsModel prescriptionsModel) {
        prescriptionsRepository.delete(prescriptionsModel);
    }
}
