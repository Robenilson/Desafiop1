package com.roben.demo.controlles;

import com.roben.demo.domain.transaction.Transaction;
import com.roben.demo.dtos.TransactionDTO;
import com.roben.demo.serveces.TransactionServicer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/Transactions")
public class TransactionController {

    @Autowired
    TransactionServicer transactionServicer;



    @PostMapping
    public ResponseEntity<Transaction> createUser(@RequestBody TransactionDTO  transaction ) throws Exception{
        Transaction newTransaction=  this.transactionServicer.createTransaction(transaction);
        return new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }



}
