package com.example.blockchainhealthcare.model;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class PatientRecordDTO {
    private String patientName;
    private int age;
    private String diagnosis;

    public PatientRecordDTO(String patientName, int age, String diagnosis) {
        this.patientName = patientName;
        this.age = age;
        this.diagnosis = diagnosis;
    }

    @Override
    public String toString() {
        return "PatientRecord{" +
                "patientName='" + patientName + '\'' +
                ", age=" + age +
                ", diagnosis='" + diagnosis + '\'' +
                '}';
    }
}
