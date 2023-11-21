package com.example.saude.repositories;

import com.example.saude.model.PrescriptionsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrescriptionsRepository extends JpaRepository<PrescriptionsModel, UUID> {
    boolean existsByDiagnostic(String diagnostic);
}
