package com.example.blockchainhealthcare.model;

import com.example.blockchainhealthcare.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BlockDTO {

    private int block_id;
    private int proof;
    private PatientRecordDTO patientRecord;
    private String previousHash;
    private String hash;
    private long timestamp;


    public BlockDTO(int block_id, int proof, PatientRecordDTO patientRecord,String previousHash,long timestamp) {
        this.block_id = block_id;
        this.proof = proof;
        this.patientRecord = patientRecord;
        this.previousHash = previousHash;
        this.hash = calculateHash();
        this.timestamp = timestamp;
    }

    public String calculateHash() {
        String data = block_id + timestamp + previousHash + patientRecord.toString();
        return StringUtil.applySHA256(data);
    }
}
