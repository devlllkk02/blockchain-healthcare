package com.example.blockchainhealthcare.controller;

import com.example.blockchainhealthcare.model.BlockDTO;
import com.example.blockchainhealthcare.model.BlockchainDTO;
import com.example.blockchainhealthcare.model.PatientRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/blockchain")
public class BlockchainController {

    private final BlockchainDTO blockchain;

    @Autowired
    public BlockchainController(BlockchainDTO blockchain) {
        this.blockchain = blockchain;
    }

    @GetMapping("/full")
    public ArrayList<BlockDTO> getFullBlockchain() {
        return blockchain.getChain();
    }

    @GetMapping("/valid")
    public String checkBlockchainValidity() {
        boolean isValid = blockchain.isChainValid();
        return "Is Blockchain Valid? " + isValid;
    }

    @PostMapping("/mine")
    public BlockDTO mineNewBlock(@RequestBody PatientRecordDTO patientRecords) {
        int previousProof = blockchain.getPreviousBlock().getIndex();
        int proof = blockchain.proofOfWork(previousProof);
        String previousHash = blockchain.getPreviousBlock().getHash();
        return blockchain.createBlock(proof, previousHash, patientRecords);
    }
}
