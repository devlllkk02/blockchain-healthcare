package com.example.blockchainhealthcare.model;

import com.example.blockchainhealthcare.utils.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Getter
@Setter
@Component
public class BlockchainDTO {
    private ArrayList<BlockDTO> chain;

    public BlockchainDTO() {
        this.chain = new ArrayList<>();
        createBlock(1 ,"0",new PatientRecordDTO("Genesis Patient",0,"Genesis Disease"));
    }

    public BlockDTO createBlock(int proof, String previousHash, PatientRecordDTO patientRecord) {
        BlockDTO block = new BlockDTO(chain.size() + 1, System.currentTimeMillis(), previousHash, patientRecord);
        chain.add(block);
        return block;
    }

    public BlockDTO getPreviousBlock() {
        return chain.get(chain.size() - 1);
    }

    public int proofOfWork(int previousProof) {
        int newProof = 1;
        boolean checkProof = false;

        while (!checkProof) {
            String hashOperation = StringUtil.applySHA256(String.valueOf(newProof * newProof - previousProof * previousProof));

            if (hashOperation.startsWith("0000")) {
                checkProof = true;
            } else {
                newProof++;
            }
        }

        return newProof;
    }

    public String hash(BlockDTO block) {
        String encodedBlock = block.toString(); // Assuming you have a toString method in your BlockDTO class
        return StringUtil.applySHA256(encodedBlock);
    }

    public boolean isChainValid() {
        BlockDTO previousBlock = chain.get(0);
        int blockIndex = 1;

        while (blockIndex < chain.size()) {
            BlockDTO block = chain.get(blockIndex);

            if (!block.getPreviousHash().equals(hash(previousBlock))) {
                return false;
            }

            int previousProof = previousBlock.getIndex();
            int proof = block.getIndex();
            String hashOperation = StringUtil.applySHA256(String.valueOf(proof * proof - previousProof * previousProof));

            if (!hashOperation.startsWith("0000")) {
                return false;
            }

            previousBlock = block;
            blockIndex++;
        }
        return true;
    }
}
