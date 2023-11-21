package com.example.saude.controller;

import com.example.saude.dtos.PrescriptionsDto;
import com.example.saude.model.PrescriptionsModel;
import com.example.saude.services.PrescriptionsService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/prescriptions")
public class PrescriptionsController {

    final PrescriptionsService prescriptionsService;

    public PrescriptionsController(PrescriptionsService prescriptionsService) {
        this.prescriptionsService = prescriptionsService;
    }

    @PostMapping
    public ResponseEntity<Object> savePrescription(@RequestBody @Valid PrescriptionsDto prescriptionsDto) {
        if(prescriptionsService.existsByDiagnostic(prescriptionsDto.getDiagnostic())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: The diagnostic has already been made");
        }

        var prescriptionModel = new PrescriptionsModel();
        BeanUtils.copyProperties(prescriptionsDto, prescriptionModel);
        prescriptionModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(prescriptionsService.save(prescriptionModel));
    }

    @GetMapping
    public ResponseEntity<List<PrescriptionsModel>> getAllPrescriptions(){
        return ResponseEntity.status(HttpStatus.OK).body(prescriptionsService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePrescriptions(@PathVariable(value = "id") UUID id){
        Optional<PrescriptionsModel> prescriptionsModelOptional = prescriptionsService.findById(id);
        if(!prescriptionsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(prescriptionsModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePrescription(@PathVariable(value = "id") UUID id){
        Optional<PrescriptionsModel> prescriptionsModelOptional = prescriptionsService.findById(id);
        if(!prescriptionsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found.");
        }
        prescriptionsService.delete(prescriptionsModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Prescription deleted successfully.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePrescription(@PathVariable(value = "id") UUID id,
                                                     @RequestBody @Valid PrescriptionsDto prescriptionsDto){
        Optional<PrescriptionsModel> prescriptionsModelOptional = prescriptionsService.findById(id);
        if(!prescriptionsModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prescription not found.");
        }
        var prescriptionModel = prescriptionsModelOptional.get();
        prescriptionModel.setDiagnostic(prescriptionsDto.getDiagnostic());
        prescriptionModel.setDosage(prescriptionsDto.getDosage());
        prescriptionModel.setFrequency(prescriptionsDto.getFrequency());
        prescriptionModel.setMedicament(prescriptionsDto.getMedicament());
        prescriptionModel.setDoctorSignature(prescriptionsDto.getDoctorSignature());

        return ResponseEntity.status(HttpStatus.OK).body(prescriptionsService.save(prescriptionModel));
    }

}
