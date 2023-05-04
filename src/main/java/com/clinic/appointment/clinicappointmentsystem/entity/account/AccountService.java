package com.clinic.appointment.clinicappointmentsystem.entity.account;

import com.clinic.appointment.clinicappointmentsystem.entity.doctor.DoctorRepo;
import com.clinic.appointment.clinicappointmentsystem.entity.patient.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {

    private final DoctorRepo doctorRepo;
    private final PatientRepo patientRepo;

    @Autowired
    public AccountService(DoctorRepo doctorRepo, PatientRepo patientRepo) {
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    public List<AccountEntity> getAccountsByFirstNameAndLastName(String firstName, String lastName) {
        List<AccountEntity> accounts = new ArrayList<>();
        if (doctorRepo.findDoctorEntitiesByFirstNameAndLastName(firstName, lastName) != null) {
            accounts.addAll(doctorRepo.findDoctorEntitiesByFirstNameAndLastName(firstName, lastName));
        }
        if (patientRepo.findPatientEntitiesByFirstNameAndLastName(firstName, lastName) != null) {
            accounts.addAll(patientRepo.findPatientEntitiesByFirstNameAndLastName(firstName, lastName));
        }
        return accounts;
    }

    public List<AccountEntity> getAllAccounts() {
        List<AccountEntity> accounts = new ArrayList<>();

        accounts.addAll(doctorRepo.findAll());
        accounts.addAll(patientRepo.findAll());

        return accounts;
    }

    public List<AccountEntity> getAccountsByEmail(String email) {
        List<AccountEntity> accounts = new ArrayList<>();
        accounts.addAll(patientRepo.findPatientEntitiesByEmail(email));
        accounts.addAll(doctorRepo.findDoctorEntitiesByEmail(email));
        return accounts;
    }

    public List<AccountEntity> getAccountsByType(String accountType) {
        List<AccountEntity> accounts = new ArrayList<>();
        if (accountType.equalsIgnoreCase("doctor")) {
            accounts.addAll(doctorRepo.findAll());
        } else if (accountType.equalsIgnoreCase("patient")) {
            accounts.addAll(patientRepo.findAll());
        }
        return accounts;
    }

    public long getTotalAccountsCount() {
        return doctorRepo.count() + patientRepo.count();
    }
}
