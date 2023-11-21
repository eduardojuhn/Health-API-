package com.example.saude.dtos;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

public class PrescriptionsDto {

    @NotBlank
    private String medicament;
    @NotBlank
    private String diagnostic;
    @NotBlank
    private String dosage;
    @NotBlank
    private String frequency;

    public String getMedicament() {
        return medicament;
    }

    public void setMedicament(String medicament) {
        this.medicament = medicament;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDoctorSignature() {
        return doctorSignature;
    }

    public void setDoctorSignature(String doctorSignature) {
        this.doctorSignature = doctorSignature;
    }

    @NotBlank
    private String doctorSignature;

}
