package com.springsecurity.controller;

import com.springsecurity.model.Patient;
import com.springsecurity.service.HospitalServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class HospitalController {

    private final HospitalServiceImpl hospitalService;

    @PostMapping("/registerPatient")
    public ResponseEntity<?> registerPatient(@RequestBody Patient patient){
       return ResponseEntity.ok(hospitalService.registerPatient(patient));
    }


    @GetMapping("/viewPatients")
    public ResponseEntity<?> viewPatients(){
       return ResponseEntity.ok(hospitalService.viewPatients());
    }


    @DeleteMapping("/deletePatient/{patientId}")
    public ResponseEntity<?> deletePatient(@PathVariable Long patientId){
       return ResponseEntity.ok(hospitalService.deletePatient(patientId));
    }


    @GetMapping("/hospitalServices")
    public ResponseEntity<?> hospitalServices(){
       return ResponseEntity.ok(hospitalService.hospitalServices());
    }


    @GetMapping("/contact")
    public ResponseEntity<?> contact(){
       return ResponseEntity.ok(hospitalService.contact());
    }

}
