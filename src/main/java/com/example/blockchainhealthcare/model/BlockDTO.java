package com.example.blockchainhealthcare.model;

import com.example.blockchainhealthcare.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockDTO {

    private int index;
    private long timestamp;
    private String hash;
    private String previousHash;
    private PatientRecordDTO patientRecord;

    public BlockDTO(int index, long timestamp, String previousHash, PatientRecordDTO patientRecord) {
        this.index = index;
        this.timestamp = timestamp;
        this.previousHash = previousHash;
        this.patientRecord = patientRecord;
        this.hash = calculateHash();
    }

    public String calculateHash() {
        String data = index + timestamp + previousHash + patientRecord.toString();
        return StringUtil.applySHA256(data);
    }
}
