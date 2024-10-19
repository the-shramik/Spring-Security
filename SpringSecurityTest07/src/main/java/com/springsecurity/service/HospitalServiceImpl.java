package com.springsecurity.service;

import com.springsecurity.model.Patient;
import com.springsecurity.repository.IPatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HospitalServiceImpl {

    private final IPatientRepository patientRepository;

    public Patient registerPatient(@RequestBody Patient patient){
       return patientRepository.save(patient);
    }


    public List<Patient> viewPatients(){
        return patientRepository.findAll();
    }



    public String deletePatient(Long patientId){
         patientRepository.deleteById(patientId);

         if(patientRepository.findById(patientId).isEmpty()){
             return "Patient deleted!";
         }
         else{
             return "Patient not deleted!";
         }
    }



    public String hospitalServices(){
       return "Here are all the hospital services";
    }

    public String contact(){
        return "Here are the contact details..!";
    }
}
